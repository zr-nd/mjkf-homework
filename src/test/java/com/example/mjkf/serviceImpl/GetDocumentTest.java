package com.example.mjkf.serviceImpl;


import com.example.mjkf.po.Paper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetDocumentTest{

    @Autowired
    private GetDocument getDocument;



    @Test
    public void getPaperInfoTest(){
        String url = "https://ieeexplore.ieee.org/document/8624118";
        Paper res = getDocument.getPaperInfo(url);
        if(res.getTitle()!=null) {
            assertEquals("Welcome from General Chair", res.getTitle());
        }
    }


//    @Test
//    public void BeginTest(){
//        ArrayList<Paper> res = getDocument.Begin(2);
//        if(res.size()>0){
//            assertTrue(res.size()>0);
//        }else{
//            assertEquals(0,res.size());
//        }
//    }


}


