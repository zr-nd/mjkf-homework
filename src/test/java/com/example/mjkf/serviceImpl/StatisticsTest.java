package com.example.mjkf.serviceImpl;


import com.example.mjkf.dao.StatisticsMapper;
import com.example.mjkf.po.Author;
import com.example.mjkf.po.Conference;
import com.example.mjkf.po.Paper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsTest {

    @Autowired
    private Statistics statistics;


    @Test
    public void statisticsPaperTest(){
        StatisticsMapper statisticsMapper=mock(StatisticsMapper.class);
        //List<Paper> result = statistics.statisticsPaper();
        List<Paper> test = new ArrayList<>();
        when(statisticsMapper.selectPaperByRefer()).thenReturn(test);
        Statistics statistics=new Statistics();
        try{
            Field declaredField = Statistics.class.getDeclaredField("statisticsMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,statistics,statisticsMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(statistics.statisticsPaper().equals(test));
        /*Paper paper1 = new Paper();
        Paper p2 = new Paper();
        Paper p3 = new Paper();
        Paper p4 = new Paper();
        Paper p5 = new Paper();
        Paper p6 = new Paper();
        Paper p7 = new Paper();
        Paper p8 = new Paper();
        Paper p9 = new Paper();
        Paper p10 = new Paper();
        paper1.setTitle("Software Documentation Issues Unveiled");
        paper1.setReferenceCount(127);
        p2.setTitle("Deep learning code fragments for code clone detection");
        p2.setReferenceCount(115);
        p3.setTitle("Could I Have a Stack Trace to Examine the Dependency Conflict Issue?");
        p3.setReferenceCount(105);
        p4.setTitle("A System Identification Based Oracle for Control-CPS Software Fault Localization");
        p4.setReferenceCount(101);
        p5.setTitle("Going Farther Together: The Impact of Social Capital on Sustained Participation in Open Source");
        p5.setReferenceCount(100);
        p6.setTitle("A SEALANT for Inter-App Security Holes in Android");
        p6.setReferenceCount(95);
        p7.setTitle("Multi-objective Software Effort Estimation");
        p7.setReferenceCount(92);
        p8.setTitle("Distilling Neural Representations of Data Structure Manipulation using fMRI and fNIRS");
        p8.setReferenceCount(92);
        p9.setTitle("VFix: Value-Flow-Guided Precise Program Repair for Null Pointer Dereferences");
        p9.setReferenceCount(92);
        p10.setTitle("Emotions Extracted from Text vs. True Emotions??n Empirical Evaluation in SE Context");
        p10.setReferenceCount(89);
        List<Paper> test = new ArrayList<>();
        test.add(paper1);
        test.add(p2);
        test.add(p3);
        test.add(p4);
        test.add(p5);
        test.add(p6);
        test.add(p7);
        test.add(p8);
        test.add(p9);
        test.add(p10);
        assertEquals(result.size(),test.size());
        for(int i=0;i<result.size();i++){
            judgeObject(result.get(i),test.get(i));
        }

         */
    }
    @Test
    public void statisticsConferenceTest(){
        StatisticsMapper statisticsMapper=mock(StatisticsMapper.class);
        List<Conference> result = statistics.statisticConference();
        Conference c1 = new Conference();
        Conference c2 = new Conference();
        Conference c3 = new Conference();
        Conference c4 = new Conference();
        Conference c5 = new Conference();

        c1.setName("2015 IEEE/ACM 37th IEEE International Conference on Software Engineering");
        c1.setValue(308);
        c2.setName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        c2.setValue(160);
        c3.setName("2018 IEEE/ACM 40th International Conference on Software Engineering (ICSE)");
        c3.setValue(158);
        c4.setName("2017 32nd IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        c4.setValue(118);
        c5.setName("2019 IEEE/ACM 41st International Conference on Software Engineering (ICSE)");
        c5.setValue(109);


        List<Conference> test = new ArrayList<>();
        test.add(c1);
        test.add(c2);
        test.add(c3);
        test.add(c4);
        test.add(c5);

        when(statisticsMapper.selectConference()).thenReturn(test);
        Statistics statistics=new Statistics();
        try{
            Field declaredField = Statistics.class.getDeclaredField("statisticsMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,statistics,statisticsMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(statistics.statisticConference().equals(test));

        /*
        for(int i=0;i<5;i++){
            judgeConference(result.get(i),test.get(i));
        }

         */
    }

    @Test
    public void getMostAutherTest(){
        StatisticsMapper statisticsMapper=mock(StatisticsMapper.class);
        //List<Author> result  = statistics.getMostAuthor();
        List<Author> test = new ArrayList<>();
        when(statisticsMapper.getMostAuthor()).thenReturn(test);
        Statistics statistics=new Statistics();
        try{
            Field declaredField = Statistics.class.getDeclaredField("statisticsMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,statistics,statisticsMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(statistics.getMostAuthor().equals(test));

        /*Author a1 = new Author();
        a1.setName(" Y. Liu");
        a1.setCount(32);
        Author a2 = new Author();
        a2.setName(" J. Sun");
        a2.setCount(23);
        Author a3 = new Author();
        a3.setName(" L. Zhang");
        a3.setCount(20);
        Author a4 = new Author();
        a4.setName(" H. Zhang");
        a4.setCount(18);
        Author a5 = new Author();
        a5.setName(" G. Bavota");
        a5.setCount(16);
        Author a6 = new Author();
        a6.setName(" Z. Xing");
        a6.setCount(15);
        Author a7 = new Author();
        a7.setName(" D. Lo");
        a7.setCount(15);
        Author a8 = new Author();
        a8.setName(" X. Zhang");
        a8.setCount(15);
        Author a9 = new Author();
        a9.setName(" M. Di Penta");
        a9.setCount(14);
        Author a10 = new Author();
        a10.setName(" S. Apel");
        a10.setCount(14);
        test.add(a1);
        test.add(a2);
        test.add(a3);
        test.add(a4);
        test.add(a5);
        test.add(a6);
        test.add(a7);
        test.add(a8);
        test.add(a9);
        test.add(a10);
        assertEquals(result.size(),test.size());
        for(int i=0;i<result.size();i++){
            judgeAuthor(result.get(i),test.get(i));
        }

         */
    }

    private void judgeObject(Paper autal,Paper expect){
        assertEquals(autal.getTitle(),expect.getTitle());
        assertEquals(autal.getReferenceCount(),expect.getReferenceCount());
    }

    private void judgeConference(Conference autal,Conference expect){
        assertEquals(autal.getName(),expect.getName());
        assertEquals(autal.getValue(),expect.getValue());
    }

    private void judgeAuthor(Author autal,Author expect){
        assertEquals(autal.getName(),expect.getName());
        assertEquals(autal.getCount(),expect.getCount());
    }


}
