package com.example.mjkf.serviceImpl;

import com.example.mjkf.dao.PortrayMapper;
import com.example.mjkf.po.*;
import com.example.mjkf.vo.PaperVO;
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
class PortrayTest {
    @Autowired
    private Portray portray;

    //测试按关键词获得研究方向信息
    @Test
    public void getKeywordPortrayTest() {
        Keyword keyword=portray.getKeywordPortray("Adaptation");
        Keyword res=new Keyword();
        res.setKeyword("Adaptation");

        judgeKeyword(res,keyword);
    }

    //测试获得某一研究方向下的top论文
    @Test
    void selectTopPaperinFieldTest() {
        PortrayMapper portrayMapper=mock(PortrayMapper.class);
        //List<PaperVO> autal=portray.selectTopPaperinField("BSD");
        List<PaperVO> expect=new ArrayList<>();
        when(portrayMapper.selectTopPaperinField("BSD")).thenReturn(expect);
        Portray portray=new Portray();
        try{
            Field declaredField = Portray.class.getDeclaredField("portrayMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,portray,portrayMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(portray.selectTopPaperinField("BSD").equals(expect));

        /*PaperVO paper=new PaperVO();
        paper.setTitle("The Evolution of C Programming Practices: A Study of the Unix Operating System 1973-2015");
        paper.setAffiliations("Dept. of Manage. Sci. & Technol., Athens Univ. of Econ. & Bus., Athens, Greece; Dept. of Manage. Sci. & Technol., Athens Univ. of Econ. & Bus., Athens, Greece; Dept. of Manage. Sci. & Technol., Athens Univ. of Econ. & Bus., Athens, Greece");
        paper.setAuthors("D. Spinellis; P. Louridas; M. Kechagia");
        paper.setAuthorKeyWord("C;coding style;coding practices;Unix;BSD;FreeBSD");
        paper.setDocID("IEEE Conferences");
        paper.setPaperAbstract("Tracking long-term progress in engineering and applied science allows us to take stock of things we have achieved, appreciate the factors that led to them, and set realistic goals for where we want to go. We formulate seven hypotheses associated with the long term evolution of C programming in the Unix operating system, and examine them by extracting, aggregating, and synthesising metrics from 66 snapshots obtained from a synthetic software configuration management repository covering a period of four decades. We found that over the years developers of the Unix operating system appear to have evolved their coding style in tandem with advancements in hardware technology, promoted modularity to tame rising complexity, adopted valuable new language features, allowed compilers to allocate registers on their behalf, and reached broad agreement regarding code formatting. The progress we have observed appears to be slowing or even reversing prompting the need for new sources of innovation to be discovered and followed.");
        paper.setPublicTitle("2016 IEEE/ACM 38th International Conference on Software Engineering (ICSE)");
        paper.setYear(2016);
        paper.setStartPage(748);
        paper.setEndPage(759);
        paper.setPdfLink("https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7886953");
        paper.setReferenceCount(63);
        paper.setPublisher("IEEE");
        expect.add(paper);
        judgePaper(autal.get(0),expect.get(0));

         */
    }

    //测试获得研究方向活跃度排名
    @Test
    void selectPopFieldTest() {
        PortrayMapper portrayMapper=mock(PortrayMapper.class);
        //List<TopField> autal=portray.selectPopField();
        List<TopField> expect=new ArrayList<>();

        when(portrayMapper.selectPopField()).thenReturn(expect);
        Portray portray=new Portray();
        try{
            Field declaredField = Portray.class.getDeclaredField("portrayMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,portray,portrayMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(portray.selectPopField().equals(expect));

        /*TopField k1=new TopField("Static Analysis");
        TopField k2=new TopField("empirical study");
        TopField k3=new TopField("Android");
        TopField k4=new TopField("software testing");
        TopField k5=new TopField("Testing");
        TopField k6=new TopField("Refactoring");
        TopField k7=new TopField("software engineering");
        TopField k8=new TopField("deep learning");
        TopField k9=new TopField("dynamic analysis");
        TopField k10=new TopField("Symbolic Execution");

        expect.add(k1);
        expect.add(k2);
        expect.add(k3);
        expect.add(k4);
        expect.add(k5);
        expect.add(k6);
        expect.add(k7);
        expect.add(k8);
        expect.add(k9);
        expect.add(k10);
        assertEquals(autal.size(),expect.size());
        for(int i=0;i<10;i++) {
            //assertEquals(autal.get(i).getAuthorKeyWord(), expect.get(i).getAuthorKeyWord());
        }

         */
    }

    //测试研究方向下的top作者
    @Test
    void selectTopAuthorInFieldTest(){
        PortrayMapper portrayMapper=mock(PortrayMapper.class);
        List<TopAuthor> autal=portray.selectTopAuthorInField("Testing");
        List<TopAuthor> expect=new ArrayList<>();

        when(portrayMapper.selectTopAuthorInField("Testing")).thenReturn(expect);
        Portray portray=new Portray();
        try{
            Field declaredField = Portray.class.getDeclaredField("portrayMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,portray,portrayMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(portray.selectTopAuthorInField("Testing").equals(expect));

        /*TopAuthor t1=new TopAuthor();
        t1.setAuthor("Z. He");
        TopAuthor t2=new TopAuthor();
        t2.setAuthor("Q. Wang");
        TopAuthor t3=new TopAuthor();
        t3.setAuthor("E. Huang");
        TopAuthor t4=new TopAuthor();
        t4.setAuthor("Y. Pei");
        TopAuthor t5=new TopAuthor();
        t5.setAuthor("H. Yuan");
        TopAuthor t6=new TopAuthor();
        t6.setAuthor("Y. Chen");
        TopAuthor t7=new TopAuthor();
        t7.setAuthor("S. Jana");
        TopAuthor t8=new TopAuthor();
        t8.setAuthor("B. Ray");
        TopAuthor t9=new TopAuthor();
        t9.setAuthor("Y. Tian");
        TopAuthor t10=new TopAuthor();
        t10.setAuthor("K. Pei");
        expect.add(t1);
        expect.add(t2);
        expect.add(t3);
        expect.add(t4);
        expect.add(t5);
        expect.add(t6);
        expect.add(t7);
        expect.add(t8);
        expect.add(t9);
        expect.add(t10);

        assertEquals(autal.size(),expect.size());
        for(int i=0;i<autal.size();i++){
            //assertEquals(autal.get(i).getAuthor(),expect.get(i).getAuthor());
        }

         */
    }

    //测试研究方向的top机构
    @Test
    void selectTopAffiliationInFieldTest(){
        PortrayMapper portrayMapper=mock(PortrayMapper.class);
        List<TopAffiliation> autal=portray.selectTopAffiliationInField("Testing");
        List<TopAffiliation> expect=new ArrayList<>();
        when(portrayMapper.selectTopAffiliationInField("Testing")).thenReturn(expect);
        Portray portray=new Portray();
        try{
            Field declaredField = Portray.class.getDeclaredField("portrayMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,portray,portrayMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(portray.selectTopAffiliationInField("Testing").equals(expect));

        /*TopAffiliation t1=new TopAffiliation();
        t1.setName("The Hong Kong Polytechnic University, Hong Kong SAR of China");
        TopAffiliation t2=new TopAffiliation();
        t2.setName("The Chinese University of Hong Kong, Hong Kong SAR of China");
        TopAffiliation t3=new TopAffiliation();
        t3.setName("Singapore University of Technology and Design");
        TopAffiliation t4=new TopAffiliation();
        t4.setName("Singapore Management University");
        TopAffiliation t5=new TopAffiliation();
        t5.setName("Zhejiang University and Alibaba-Zhejiang University Joint Institute of Frontier Technologies");
        TopAffiliation t6=new TopAffiliation();
        t6.setName("Concordia University, Canada");
        TopAffiliation t7=new TopAffiliation();
        t7.setName("Ohio State Univ., Columbus, OH, USA");
        TopAffiliation t8=new TopAffiliation();
        t8.setName("Fed. Univ. of Campina Grande, Campina Grande, Brazil");
        TopAffiliation t9=new TopAffiliation();
        t9.setName("Fed. Univ. of Pernambuco, Recife, Brazil");
        TopAffiliation t10=new TopAffiliation();
        t10.setName("Google Inc., Mountain View, CA, USA");

        expect.add(t1);
        expect.add(t2);
        expect.add(t3);
        expect.add(t4);
        expect.add(t5);
        expect.add(t6);
        expect.add(t7);
        expect.add(t8);
        expect.add(t9);
        expect.add(t10);

        assertEquals(autal.size(),expect.size());
        for(int i=0;i<autal.size();i++){
            //assertEquals(autal.get(i).getName(),expect.get(i).getName());
        }

         */
    }

    void judgeKeyword(Keyword expect,Keyword actual){
        assertEquals(expect.getKeyword(),actual.getKeyword());
    }

    void judgePaper(PaperVO autal, PaperVO expect){
        assertEquals(autal.getTitle(), expect.getTitle());
        assertEquals(autal.getAuthors(), expect.getAuthors());
        assertEquals(autal.getAffiliations(), expect.getAffiliations());
        assertEquals(autal.getPublicTitle(), expect.getPublicTitle());
        assertEquals(autal.getYear(), expect.getYear());
        assertEquals(autal.getStartPage(), expect.getStartPage());
        assertEquals(autal.getEndPage(), expect.getEndPage());
        assertEquals(autal.getPdfLink(), expect.getPdfLink());
        assertEquals(autal.getAuthorKeyWord(), expect.getAuthorKeyWord());
        assertEquals(autal.getPublisher(), expect.getPublisher());
        assertEquals(autal.getDocID(),expect.getDocID());
        assertEquals(autal.getPaperAbstract(), expect.getPaperAbstract());
    }

}