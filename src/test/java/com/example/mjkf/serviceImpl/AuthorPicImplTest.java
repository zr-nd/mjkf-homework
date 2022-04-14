package com.example.mjkf.serviceImpl;

import com.example.mjkf.dao.AuthorPicMapper;
import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.Paper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorPicImplTest {
    @Autowired
    private AuthorPicImpl authorPic;



    //测试作者按活跃度排名
    @Test
    void getMostAuthorPic() {
        AuthorPicMapper authorPicMapper=mock(AuthorPicMapper.class);
        //List<AuthorPic> autal=authorPic.getMostAuthorPic();
        List<AuthorPic> expect=new ArrayList<>();
        when(authorPicMapper.getMostAuthorPic()).thenReturn(expect);
        AuthorPicImpl authorPic=new AuthorPicImpl();
        try{
            Field declaredField = AuthorPicImpl.class.getDeclaredField("authorPicMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,authorPic,authorPicMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(authorPic.getMostAuthorPic().equals(expect));

        /*AuthorPic a1=new AuthorPic();
        AuthorPic a2=new AuthorPic();
        AuthorPic a3=new AuthorPic();
        AuthorPic a4=new AuthorPic();
        AuthorPic a5=new AuthorPic();
        AuthorPic a6=new AuthorPic();
        AuthorPic a7=new AuthorPic();
        AuthorPic a8=new AuthorPic();
        AuthorPic a9=new AuthorPic();
        AuthorPic a10=new AuthorPic();

        a1.setAuthor("Y. Liu");
        a2.setAuthor("X. Xia");
        a3.setAuthor("M. Harman");
        a4.setAuthor("J. Sun");
        a5.setAuthor("D. Lo");
        a6.setAuthor("Y. Xiong");
        a7.setAuthor("S. Apel");
        a8.setAuthor("M. Di Penta");
        a9.setAuthor("L. Zhang");
        a10.setAuthor("D. Hao");

        expect.add(a1);
        expect.add(a2);
        expect.add(a3);
        expect.add(a4);
        expect.add(a5);
        expect.add(a6);
        expect.add(a7);
        expect.add(a8);
        expect.add(a9);
        expect.add(a10);

        for(int i=0;i<10;i++){
            assertEquals(autal.get(i).getAuthor(),expect.get(i).getAuthor());
        }

         */
    }

    //测试按姓名和机构获得作者画像
    @Test
    void getAuthorPicByName() {
        AuthorPic autal=authorPic.getAuthorPicByName("J. Penix","Google, USA");
        AuthorPic expect=new AuthorPic("J. Penix","Google, USA",1,0);
        judgeAuthor(autal,expect);
    }

    //测试按姓名和标题获得作者画像
    @Test
    void getAuthorPicByNameAndTitle() {
        AuthorPic autal=authorPic.getAuthorPicByNameAndTitle("B. Wei","Retrieve and Refine: Exemplar-Based Neural Comment Generation");
        AuthorPic expect=new AuthorPic("B. Wei","Peking University",1,21);
        judgeAuthor(autal,expect);
    }

    //测试根据作者获得他的论文
    @Test
    void getPapersByAuthorPic() {
        List<Paper> autal=authorPic.getPapersByAuthorPic("B. Wei","Peking University");
        Paper p=new Paper();
        p.setTitle("Retrieve and Refine: Exemplar-Based Neural Comment Generation");
        assertEquals(autal.get(0).getTitle(),p.getTitle());
    }

    //判断是否是同一作者
    void judgeAuthor(AuthorPic autal,AuthorPic expect){
        assertEquals(autal.getAuthor(),expect.getAuthor());
        assertEquals(autal.getAffiliation(),expect.getAffiliation());
        assertEquals(autal.getPapers(),expect.getPapers());
        assertEquals(autal.getRefs(),expect.getRefs());
    }
}