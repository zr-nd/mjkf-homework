package com.example.oasis.serviceImpl;

import com.example.oasis.dao.PaperMapper;
import com.example.oasis.po.Affiliation;
import com.example.oasis.po.AffiliationField;
import com.example.oasis.po.Paper;
import com.example.oasis.po.TopAffiliation;
import com.example.oasis.vo.AffiliationVO;
import com.example.oasis.vo.LinkVO;
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

class SearchTest {

    @Autowired
    private Search search;

    //单值查询测试
    @Test
    void getListPaper() {
        List<Paper> paperList=search.getListPaper("B. Wei");
        Paper paper=new Paper("Retrieve and Refine: Exemplar-Based Neural Comment Generation","B. Wei","Peking University", "2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,1250,1252, "https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952536","comment generation;program comprehension;deep learning",21, "IEEE","IEEE Conferences","Code comment generation is a crucial task in the field of automatic software development. Most previous neural comment generation systems used an encoder-decoder neural network and encoded only information from source code as input. Software reuse is common in software development. However, this feature has not been introduced to existing systems. Inspired by the traditional IR-based approaches, we propose to use the existing comments of similar source code as exemplars to guide the comment generation process. Based on an open source search engine, we first retrieve a similar code and treat its comment as an exemplar. Then we applied a seq2seq neural network to conduct an exemplar-based comment generation. We evaluate our approach on a large-scale Java corpus, and experimental results demonstrate that our model significantly outperforms the state-of-the-art methods.");
        List<Paper> result=new ArrayList<>();
        result.add(paper);
        assertEquals(paperList.size(),result.size());
        for(int i=0;i<paperList.size();i++) {
            judgeObject(result.get(i),paperList.get(i));
        }
    }

    //组合查询测试
    @Test
    void getListPaperByCombina() {

        List<Paper> paperList1=search.getListPaperByCombina("X. Chen&2019");
        List<Paper> paperList2=search.getListPaperByCombina("X. Chen&2019&Peking");
        List<Paper> paperList3=search.getListPaperByCombina("X. Chen&2019&Peking&Humanoid");

        Paper paper1=new Paper("Humanoid: A Deep Learning-Based Approach to Automated Black-box Android App Testing","Y. Li; Z. Yang; Y. Guo; X. Chen","Peking University; Peking University; Peking University; Peking University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,1070,1073,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952324","software testing;automated test input generation;graphical user interface;deep learning;mobile application;Android",14,"IEEE","IEEE Conferences","Automated input generators must constantly choose which UI element to interact with and how to interact with it, in order to achieve high coverage with a limited time budget. Currently, most black-box input generators adopt pseudo-random or brute-force searching strategies, which may take very long to find the correct combination of inputs that can drive the app into new and important states. We propose Humanoid, an automated black-box Android app testing tool based on deep learning. The key technique behind Humanoid is a deep neural network model that can learn how human users choose actions based on an app's GUI from human interaction traces. The learned model can then be used to guide test input generation to achieve higher coverage. Experiments on both open-source apps and market apps demonstrate that Humanoid is able to reach higher coverage, and faster as well, than the state-of-the-art test input generators. Humanoid is open-sourced at https://github.com/yzygitzh/Humanoid and a demo video can be found at https://youtu.be/PDRxDrkyORs.");
        Paper paper2=new Paper("Better Development of Safety Critical Systems: Chinese High Speed Railway System Development Experience Report","Z. Wu; J. Liu; X. Chen","East China Normal University; East China Normal University; R&D Institute, CASCO Signal Ltd.","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,1216,1217,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952294","SysML;Formal Method;Model-Driven;SAT",7,"IEEE","IEEE Conferences","Ensure the correctness of safety critical systems play a key role in the worldwide software engineering. Over the past years we have been helping CASCO Signal Ltd which is the Chinese biggest high speed railway company to develop high speed railway safety critical software. We have also contributed specific methods for developing better safety critical software, including a search-based model-driven software development approach which uses SysML diagram refinement method to construct SysML model and SAT solver to check the model. This talk aims at sharing the challenge of developing high speed railway safety critical system, what we learn from develop a safety critical software with a Chinese high speed railway company, and we use ZC subsystem as a case study to show the systematic model-driven safety critical software development method.");
        Paper paper3=new Paper("MAP-Coverage: A Novel Coverage Criterion for Testing Thread-Safe Classes","Z. Wang; Y. Zhao; S. Liu; J. Sun; X. Chen; H. Lin","Tianjin University; Tianjin University; Tianjin University; Singapore Management University; Nantong University; Tianjin University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,722,734,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952403","Memory Access Pattern;Coverage Criteria;Thread Safe Classes;Concurrency Bugs",48,"IEEE","IEEE Conferences","Concurrent programs must be thoroughly tested, as concurrency bugs are notoriously hard to detect. Code coverage criteria can be used to quantify the richness of a test suite (e.g., whether a program has been tested sufficiently) or provide practical guidelines on test case generation (e.g., as objective functions used in program fuzzing engines). Traditional code coverage criteria are, however, designed for sequential programs and thus ineffective for concurrent programs. In this work, we introduce a novel code coverage criterion for testing thread-safe classes called MAP-coverage (short for memory-access patterns). The motivation is that concurrency bugs are often correlated with certain memory-access patterns, and thus it is desirable to comprehensively cover all memory-access patterns. Furthermore, we propose a testing method for maximizing MAP-coverage. Our method has been implemented as a self-contained toolkit, and the experimental results on 20 benchmark programs show that our toolkit outperforms existing testing methods. Lastly, we show empirically that there exists positive correlation between MAP-coverage and the effectiveness of a set of test executions.");
        Paper paper4=new Paper("Combining Spectrum-Based Fault Localization and Statistical Debugging: An Empirical Study","J. Jiang; R. Wang; Y. Xiong; X. Chen; L. Zhang","Peking University; Peking University; Peking University; Sun Yat-sen University; Peking University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,502,514,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952344","Software engineering, Fault localization, Program debugging",79,"IEEE","IEEE Conferences","Program debugging is a time-consuming task, and researchers have proposed different kinds of automatic fault localization techniques to mitigate the burden of manual debugging. Among these techniques, two popular families are spectrum-based fault localization (SBFL) and statistical debugging (SD), both localizing faults by collecting statistical information at runtime. Though the ideas are similar, the two families have been developed independently and their combinations have not been systematically explored. In this paper we perform a systematical empirical study on the combination of SBFL and SD. We first build a unified model of the two techniques, and systematically explore four types of variations, different predicates, different risk evaluation formulas, different granularities of data collection, and different methods of combining suspicious scores. Our study leads to several findings. First, most of the effectiveness of the combined approach contributed by a simple type of predicates: branch conditions. Second, the risk evaluation formulas of SBFL significantly outperform that of SD. Third, fine-grained data collection significantly outperforms coarse-grained data collection with a little extra execution overhead. Fourth, a linear combination of SBFL and SD predicates outperforms both individual approaches. According to our empirical study, we propose a new fault localization approach, PREDFL (Predicate-based Fault Localization), with the best configuration for each dimension under the unified model. Then, we explore its complementarity to existing techniques by integrating PREDFL with a state-of-the-art fault localization framework. The experimental results show that PREDFL can further improve the effectiveness of state-of-the-art fault localization techniques. More concretely, integrating PREDFL results in an up to 20.8% improvement w.r.t the faults successfully located at Top-1, which reveals that PREDFL complements existing techniques.");
//        Paper paper5=new Paper("Global Optimization of Numerical Programs Via Prioritized Stochastic Algebraic Transformations","X. Wang; H. Wang; Z. Su; E. Tang; X. Chen; W. Shen; Z. Chen; L. Wang; X. Zhang; X. Li","\"Nanjing University\"\" China; Nanjing University\"\" China; ETH Zurich\"\" Switzerland / UC Davis\"\" United States; Nanjing University\"\" China; Nanjing University\"\" China; Nanjing University\"\" China; Nanjing University\"\" China; Nanjing University\"\" China; Nanjing University\"\" China; Nanjing University\"\" China\"","2019 IEEE/ACM 41st International Conference on Software Engineering (ICSE)",2019,1131,1141,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8812093","program optimization;numerical analysis;program transformationOptimization;Software;Benchmark testing;Tools;Measurement;Computer science;Computer bugsalgebra;optimisationresource-limited scenarios;floating-point arithmetic;infinite-precision numerical program;resulting code;global fashion;numerical value;optimized traces;numerical benchmarks;program structures;real-world code;numerical bugs;numerical programs;prioritized stochastic algebraic transformations;safety-critical;resource-limited areas;accumulated rounding errors;numerical code;global optimization framework;numerical optimization tool",41,"IEEE","IEEE Conferences","\"Numerical code is often applied in the safety-critical\"\" but resource-limited areas. Hence\"\" it is crucial for it to be correct and efficient\"\" both of which are difficult to ensure. On one hand\"\" accumulated rounding errors in numerical programs can cause system failures. On the other hand\"\" arbitrary/infinite-precision arithmetic\"\" although accurate\"\" is infeasible in practice and especially in resource-limited scenarios because it performs thousands of times slower than floating-point arithmetic. Thus\"\" it has been a significant challenge to obtain high-precision\"\" easy-to-maintain\"\" and efficient numerical code. This paper introduces a novel global optimization framework to tackle this challenge. Using our framework\"\" a developer simply writes the infinite-precision numerical program directly following the problem's mathematical requirement specification. The resulting code is correct and easy-to-maintain\"\" but inefficient. Our framework then optimizes the program in a global fashion (i.e.\"\" considering the whole program\"\" rather than individual expressions or statements as in prior work)\"\" the key technical difficulty this work solves. To this end\"\" it analyzes the program's numerical value flows across different statements through a symbolic trace extraction algorithm\"\" and generates optimized traces via stochastic algebraic transformations guided by effective rule selection. We first evaluate our technique on numerical benchmarks from the literature; results show that our global optimization achieves significantly higher worst-case accuracy than the state-of-the-art numerical optimization tool. Second\"\" we show that our framework is also effective on benchmarks having complicated program structures\"\" which are challenging for numerical optimization. Finally\"\" we apply our framework on real-world code to successfully detect numerical bugs that have been confirmed by developers.\"");
        Paper paper5=new Paper("Global Optimization of Numerical Programs Via Prioritized Stochastic Algebraic Transformations","X. Wang; H. Wang; Z. Su; E. Tang; X. Chen; W. Shen; Z. Chen; L. Wang; X. Zhang; X. Li","Nanjing University, China; Nanjing University, China; ETH Zurich, Switzerland / UC Davis, United States; Nanjing University, China; Nanjing University, China; Nanjing University, China; Nanjing University, China; Nanjing University, China; Nanjing University, China; Nanjing University, China","2019 IEEE/ACM 41st International Conference on Software Engineering (ICSE)",2019,1131,1141,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8812093","program optimization;numerical analysis;program transformation",41,"IEEE","IEEE Conferences","Numerical code is often applied in the safety-critical, but resource-limited areas. Hence, it is crucial for it to be correct and efficient, both of which are difficult to ensure. On one hand, accumulated rounding errors in numerical programs can cause system failures. On the other hand, arbitrary/infinite-precision arithmetic, although accurate, is infeasible in practice and especially in resource-limited scenarios because it performs thousands of times slower than floating-point arithmetic. Thus, it has been a significant challenge to obtain high-precision, easy-to-maintain, and efficient numerical code. This paper introduces a novel global optimization framework to tackle this challenge. Using our framework, a developer simply writes the infinite-precision numerical program directly following the problem's mathematical requirement specification. The resulting code is correct and easy-to-maintain, but inefficient. Our framework then optimizes the program in a global fashion (i.e., considering the whole program, rather than individual expressions or statements as in prior work), the key technical difficulty this work solves. To this end, it analyzes the program's numerical value flows across different statements through a symbolic trace extraction algorithm, and generates optimized traces via stochastic algebraic transformations guided by effective rule selection. We first evaluate our technique on numerical benchmarks from the literature; results show that our global optimization achieves significantly higher worst-case accuracy than the state-of-the-art numerical optimization tool. Second, we show that our framework is also effective on benchmarks having complicated program structures, which are challenging for numerical optimization. Finally, we apply our framework on real-world code to successfully detect numerical bugs that have been confirmed by developers.");



        //测试两个检索字段组合
        List<Paper> result1=new ArrayList<>();
        result1.add(paper1);
        result1.add(paper2);
        result1.add(paper3);
        result1.add(paper4);
        result1.add(paper5);
        assertEquals(result1.size(),paperList1.size());
        for(int i=0;i<paperList1.size();i++){
            judgeObject(result1.get(i),paperList1.get(i));
        }


        //测试三个检索字段组合
        List<Paper> result2=new ArrayList<>();
        result2.add(paper1);
        result2.add(paper4);
        assertEquals(result2.size(),paperList2.size());
        for(int i=0;i<paperList2.size();i++){
            judgeObject(result2.get(i),paperList2.get(i));
        }


        //测试四个检索字段组合
        List<Paper> result3=new ArrayList<>();
        result3.add(paper1);
        assertEquals(result3.size(),paperList3.size());
        for(int i=0;i<paperList3.size();i++){
            judgeObject(result3.get(i),paperList3.get(i));
        }
    }

    //测试按机构名获得机构信息
    @Test
    void getAffiliationTest(){
        AffiliationVO autal=search.getAffiliation("CMU");
        Affiliation expect=new Affiliation("CMU",1,1,86);
        assertEquals(autal.getName(),expect.getName());
    }

    //测试机构活跃度排名
    @Test
    void getTopAffiliation(){
        PaperMapper paperMapper=mock(PaperMapper.class);
        List<TopAffiliation> autal=search.getTopAffiliation();
        List<TopAffiliation> expect=new ArrayList<>();

        when(paperMapper.selectTopAffiliation()).thenReturn(expect);
        Search search=new Search();
        try{
            Field declaredField = Search.class.getDeclaredField("paperMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,search,paperMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(search.getTopAffiliation().equals(expect));

        /*TopAffiliation a1=new TopAffiliation();
        TopAffiliation a2=new TopAffiliation();
        TopAffiliation a3=new TopAffiliation();
        TopAffiliation a4=new TopAffiliation();
        TopAffiliation a5=new TopAffiliation();
        TopAffiliation a6=new TopAffiliation();
        TopAffiliation a7=new TopAffiliation();
        TopAffiliation a8=new TopAffiliation();
        TopAffiliation a9=new TopAffiliation();
        TopAffiliation a10=new TopAffiliation();

        a1.setName("Univ. Coll. London, London, UK");
        a2.setName("Peking University");
        a3.setName("Monash University");
        a4.setName("Key Lab. of High Confidence Software Technol., Peking Univ., Beijing, China");
        a5.setName("Microsoft Res., Redmond, WA, USA");
        a6.setName("Delft Univ. of Technol., Delft, Netherlands");
        a7.setName("Singapore Management University");
        a8.setName("Singapore University of Technology and Design, Singapore");
        a9.setName("Carnegie Mellon University, USA");
        a10.setName("Univ. of Waterloo, Waterloo, ON, Canada");
        
        expect.add(a2);
        expect.add(a3);
        expect.add(a4);
        expect.add(a5);
        expect.add(a6);
        expect.add(a7);
        expect.add(a8);
        expect.add(a9);
        expect.add(a10);
        expect.add(a1);

        for(int i=0;i<10;i++)
            assertEquals(autal.get(i).getName(),expect.get(i).getName());

         */
    }

    //测试机构的研究方向
    @Test
    void getAffiliationFieldTest(){
        PaperMapper paperMapper=mock(PaperMapper.class);
        //List<AffiliationField> autal=search.getAffiliationField("Google");
        List<AffiliationField> expect=new ArrayList<>();

        when(paperMapper.getAffiliationField("Google")).thenReturn(expect);
        Search search=new Search();
        try{
            Field declaredField = Search.class.getDeclaredField("paperMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,search,paperMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(search.getAffiliationField("Google").equals(expect));

        /*AffiliationField a1=new AffiliationField();
        AffiliationField a2=new AffiliationField();
        AffiliationField a3=new AffiliationField();
        a1.setAuthorKeyWord("API Usability");
        a2.setAuthorKeyWord("Boilerplate Code");
        a3.setAuthorKeyWord("Repository Mining");
        expect.add(a1);
        expect.add(a2);
        expect.add(a3);

        assertEquals(autal.size(),expect.size());
        for(int i=0;i<autal.size();i++)
            assertEquals(autal.get(i).getAuthorKeyWord(),expect.get(i).getAuthorKeyWord());

         */
    }

    //判断两篇论文是否相同
    void judgeObject(Paper autal,Paper expect){
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



    @Test
    void getNodesLinksTest(){
        LinkVO autal=search.getNodesLinks();
        assertEquals(false,autal.getNodesList().isEmpty());
        assertEquals(false,autal.getLinksList().isEmpty());
    }

    @Test
    void getAffiliationsNodesLinksTest(){
        LinkVO autal=search.getAffiliationNodesAndLinks();
        assertEquals(false,autal.getNodesList().isEmpty());
        assertEquals(false,autal.getLinksList().isEmpty());
    }


}