package com.example.oasis.serviceImpl;

import com.example.oasis.dao.RankMapper;
import com.example.oasis.po.Affiliation;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.PublicTrend;
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
class RankTest {
    @Autowired
    private Rank rank;


    @Test
    void rankAffiliationByPapers() {
        RankMapper rankMapper = mock(RankMapper.class);
        List<Affiliation> actual=rank.rankAffiliationByPapers("All",2010,2019,"All","");
        assertEquals(true,actual.isEmpty());
        List<Affiliation> except=new ArrayList<>();
        /*Affiliation a1=new Affiliation();
        a1.setName("Peking University");
        a1.setPapers(12);
        a1.setRefs(568);
        Affiliation a2=new Affiliation();
        a2.setName("Nanjing University");
        a2.setPapers(10);
        a2.setRefs(276);
        Affiliation a3=new Affiliation();
        a3.setName("Singapore University of Technology and Design, Singapore");
        a3.setPapers(9);
        a3.setRefs(328);
        Affiliation a4=new Affiliation();
        a4.setName("University of Texas at Dallas");
        a4.setPapers(7);
        a4.setRefs(312);
        Affiliation a5=new Affiliation();
        a5.setName("Carnegie Mellon University");
        a5.setPapers(6);
        a5.setRefs(197);
        except.add(a1);
        except.add(a2);
        except.add(a3);
        except.add(a4);
        except.add(a5);

         */

        when(rankMapper.rankAffiliationByPapersConference(2010,2019,"ASE")).thenReturn(except);
        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"All","ASE;").equals(except));
        /*for(int i=0;i<5;i++){
            assertEquals(except.get(i).getName(),actual.get(i).getName());
            assertEquals(except.get(i).getPapers(),actual.get(i).getPapers());
            assertEquals(except.get(i).getRefs(),actual.get(i).getRefs());
        }*/

        when(rankMapper.rankAllAffiliationByPapers(2010,2019)).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"All","All").equals(except));

        when(rankMapper.rankAffiliationByPapersConference2(2010,2019,"ASE","ICSE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"All","ASE;ICSE;").equals(except));

        when(rankMapper.rankAffiliationByPapersField(2010,2019,"Testing")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"Tesing","All").equals(except));

        when(rankMapper.rankAffiliationByPapersFieldConference(2010,2019,"Testing","ASE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"Tesing","ASE;").equals(except));

        when(rankMapper.rankAffiliationByPapersFieldConference2(2010,2019,"Testing","ASE","ICSE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByPapers("IEEE",2010,2019,"Tesing","ASE;ICSE;").equals(except));

    }

    @Test
    void rankAffiliationByRefs() {
        RankMapper rankMapper = mock(RankMapper.class);
        List<Affiliation> actual=rank.rankAffiliationByRefs("IEEE",2010,2019,"All","");
        assertEquals(true,actual.isEmpty());
        List<Affiliation> except=new ArrayList<>();
        /*Affiliation a1=new Affiliation();
        a1.setName("Peking University");
        a1.setPapers(12);
        a1.setRefs(568);
        Affiliation a2=new Affiliation();
        a2.setName("Singapore Management University");
        a2.setPapers(6);
        a2.setRefs(336);
        Affiliation a3=new Affiliation();
        a3.setName("Singapore University of Technology and Design, Singapore");
        a3.setPapers(9);
        a3.setRefs(328);
        Affiliation a4=new Affiliation();
        a4.setName("University of Texas at Dallas");
        a4.setPapers(7);
        a4.setRefs(312);
        Affiliation a5=new Affiliation();
        a5.setName("Nanjing University");
        a5.setPapers(10);
        a5.setRefs(276);
        except.add(a1);
        except.add(a2);
        except.add(a3);
        except.add(a4);
        except.add(a5);

         */

        when(rankMapper.rankAffiliationByRefsConference(2010,2019,"ASE")).thenReturn(except);
        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"All","ASE;").equals(except));

        /*for(int i=0;i<5;i++){
            assertEquals(except.get(i).getName(),actual.get(i).getName());
            assertEquals(except.get(i).getPapers(),actual.get(i).getPapers());
            assertEquals(except.get(i).getRefs(),actual.get(i).getRefs());
        }*/

        when(rankMapper.rankAllAffiliationByRefs(2010,2019)).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"All","All").equals(except));

        when(rankMapper.rankAffiliationByRefsConference2(2010,2019,"ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"All","ASE;ISCE;").equals(except));

        when(rankMapper.rankAffiliationByRefsField(2010,2019,"Testing")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"Testing","All").equals(except));

        when(rankMapper.rankAffiliationByRefsFieldConference(2010,2019,"Testing","ASE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"Testing","ASE;").equals(except));

        when(rankMapper.rankAffiliationByRefsFieldConference2(2010,2019,"Testing","ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAffiliationByRefs("IEEE",2010,2019,"Testing","ASE;ISCE;").equals(except));

    }

    @Test
    void rankAuthorByPapers() {
        RankMapper rankMapper = mock(RankMapper.class);
        List<AuthorPic> actual=rank.rankAuthorByPapers("All",2010,2019,"All","");
        assertEquals(true,actual.isEmpty());
        List<AuthorPic> except=new ArrayList<>();
        /*AuthorPic a1=new AuthorPic();
        a1.setAuthor("J. Sun");
        a1.setPapers(8);
        a1.setRefs(294);
        AuthorPic a2=new AuthorPic();
        a2.setAuthor("L. Ma");
        a2.setPapers(4);
        a2.setRefs(150);
        AuthorPic a3=new AuthorPic();
        a3.setAuthor("L. Zhang");
        a3.setPapers(4);
        a3.setRefs(187);
        AuthorPic a4=new AuthorPic();
        a4.setAuthor("X. Xie");
        a4.setPapers(4);
        a4.setRefs(150);
        AuthorPic a5=new AuthorPic();
        a5.setAuthor("Y. Liu");
        a5.setPapers(4);
        a5.setRefs(120);
        except.add(a1);
        except.add(a2);
        except.add(a3);
        except.add(a4);
        except.add(a5);

         */
        when(rankMapper.rankAuthorByPapersConference(2010,2019,"ASE")).thenReturn(except);

        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"All","ASE;").equals(except));
        /*for(int i=0;i<5;i++){
            assertEquals(except.get(i).getAuthor(),actual.get(i).getAuthor());
            assertEquals(except.get(i).getPapers(),actual.get(i).getPapers());
            assertEquals(except.get(i).getRefs(),actual.get(i).getRefs());
        }*/

        when(rankMapper.rankAllAuthorByPapers(2010,2019)).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"All","All").equals(except));

        when(rankMapper.rankAuthorByPapersConference2(2010,2019,"ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"All","ASE;ISCE;").equals(except));

        when(rankMapper.rankAuthorByPapersField(2010,2019,"Testing")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"Testing","All").equals(except));

        when(rankMapper.rankAuthorByPapersFieldConference(2010,2019,"Testing","ASE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"Testing","ASE;").equals(except));

        when(rankMapper.rankAuthorByPapersFieldConference2(2010,2019,"Testing","ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByPapers("IEEE",2010,2019,"Testing","ASE;ISCE;").equals(except));

    }

    @Test
    void rankAuthorByRefs() {
        RankMapper rankMapper = mock(RankMapper.class);
        List<AuthorPic> actual=rank.rankAuthorByRefs("IEEE",2010,2019,"All","");
        assertEquals(true,actual.isEmpty());
        List<AuthorPic> except=new ArrayList<>();
        /*AuthorPic a1=new AuthorPic();
        a1.setAuthor("J. Sun");
        a1.setPapers(8);
        a1.setRefs(294);
        AuthorPic a2=new AuthorPic();
        a2.setAuthor("D. Lo");
        a2.setPapers(3);
        a2.setRefs(204);
        AuthorPic a3=new AuthorPic();
        a3.setAuthor("Y. Xiong");
        a3.setPapers(3);
        a3.setRefs(200);
        AuthorPic a4=new AuthorPic();
        a4.setAuthor("L. Zhang");
        a4.setPapers(4);
        a4.setRefs(187);
        AuthorPic a5=new AuthorPic();
        a5.setAuthor("X. Xia");
        a5.setPapers(3);
        a5.setRefs(186);
        except.add(a1);
        except.add(a2);
        except.add(a3);
        except.add(a4);
        except.add(a5);

         */

        when(rankMapper.rankAuthorByRefsConference(2010,2019,"ASE")).thenReturn(except);
        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"All","ASE;").equals(except));

        /*for(int i=0;i<5;i++){
            assertEquals(except.get(i).getAuthor(),actual.get(i).getAuthor());
            assertEquals(except.get(i).getPapers(),actual.get(i).getPapers());
            assertEquals(except.get(i).getRefs(),actual.get(i).getRefs());
        }*/

        when(rankMapper.rankAllAuthorByRefs(2010,2019)).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"All","All").equals(except));

        when(rankMapper.rankAuthorByRefsConference2(2010,2019,"ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"All","ASE;ISCE;").equals(except));

        when(rankMapper.rankAuthorByRefsField(2010,2019,"Testing")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"Testing","All").equals(except));

        when(rankMapper.rankAuthorByRefsFieldConference(2010,2019,"Testing","ASE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"Testing","ASE;").equals(except));

        when(rankMapper.rankAuthorByRefsFieldConference2(2010,2019,"Testing","ASE","ISCE")).thenReturn(except);
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.rankAuthorByRefs("IEEE",2010,2019,"Testing","ASE;ISCE;").equals(except));

    }

    @Test
    void getPublicTrend1() {
        RankMapper rankMapper = mock(RankMapper.class);
        //List<PublicTrend> actual=rank.getPublicTrend1("Peking University",2019,2019);
        List<PublicTrend> expect=new ArrayList<>();
        when(rankMapper.getPublicTrend1("Peking University",2019,2019)).thenReturn(expect);
        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.getPublicTrend1("Peking University",2019,2019).equals(expect));
    }

    @Test
    void getPublicTrend2() {
        RankMapper rankMapper = mock(RankMapper.class);
        List<PublicTrend> actual=rank.getPublicTrend2("Y. Liu","Nanyang Technological University, Singapore",2017,2019);
        List<PublicTrend> expect=new ArrayList<>();
        when(rankMapper.getPublicTrend2("Y. Liu","Nanyang Technological University, Singapore",2017,2019)).thenReturn(expect);
        Rank rank = new Rank();
        try{
            Field declaredField = Rank.class.getDeclaredField("rankMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,rank,rankMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(rank.getPublicTrend2("Y. Liu","Nanyang Technological University, Singapore",2017,2019).equals(expect));
    }

}