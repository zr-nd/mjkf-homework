package com.example.mjkf.serviceImpl;

import com.example.mjkf.dao.ConferPicMapper;
import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.ConferPic;
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
class GetConferPicTest {
    @Autowired
    private GetConferPic getConferPic;


    //测试会议按论文数排名
    @Test
    void getConferPic() {
        ConferPicMapper conferPicMapper=mock(ConferPicMapper.class);
        //List<ConferPic> autal=getConferPic.getConferPic();
        List<ConferPic> expect=new ArrayList<>();

        when(conferPicMapper.selectConferPic()).thenReturn(expect);
        GetConferPic getConferPic=new GetConferPic();
        try{
            Field declaredField = GetConferPic.class.getDeclaredField("conferPicMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,getConferPic,conferPicMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(getConferPic.getConferPic().equals(expect));

        /*ConferPic c1=new ConferPic();
        ConferPic c2=new ConferPic();
        ConferPic c3=new ConferPic();
        ConferPic c4=new ConferPic();
        ConferPic c5=new ConferPic();

        c1.setName("2015 IEEE/ACM 37th IEEE International Conference on Software Engineering");
        c2.setName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        c3.setName("2018 IEEE/ACM 40th International Conference on Software Engineering (ICSE)");
        c4.setName("2017 32nd IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        c5.setName("2019 IEEE/ACM 41st International Conference on Software Engineering (ICSE)");

        expect.add(c1);
        expect.add(c2);
        expect.add(c3);
        expect.add(c4);
        expect.add(c5);

        for(int i=0;i<3;i++){
            assertEquals(expect.get(i).getName(),autal.get(i).getName());
        }

         */
    }

    @Test
    void getPaperByConferenceName() {
        ConferPicMapper conferPicMapper=mock(ConferPicMapper.class);
        List<Paper> autal=getConferPic.getPaperByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        List<Paper> expect=new ArrayList<>();

        when(conferPicMapper.selectPaperByConferName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)")).thenReturn(expect);
        GetConferPic getConferPic=new GetConferPic();
        try{
            Field declaredField = GetConferPic.class.getDeclaredField("conferPicMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,getConferPic,conferPicMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(getConferPic.getPaperByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)").equals(expect));

        /*
        Paper p1=new Paper("Emotions Extracted from Text vs. True Emotions??n Empirical Evaluation in SE Context","Y. Wang","Rochester Institute of Technology","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,230,242,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952437","emotion recognition;emotion dynamics;text based NLP techniques;time series analysis;personality;organizational norms",89,"IEEE","IEEE Conferences","Emotion awareness research in SE context has been growing in recent years. Currently, researchers often rely on textual communication records to extract emotion states using natural language processing techniques. However, how well these extracted emotion states reflect people's real emotions has not been thoroughly investigated. In this paper, we report a multi-level, longitudinal empirical study with 82 individual members in 27 project teams. We collected their self-reported retrospective emotion states on a weekly basis during their year-long projects and also extracted corresponding emotions from the textual communication records. We then model and compare the dynamics of these two types of emotions using multiple statistical and time series analysis methods. Our analyses yield a rich set of findings. The most important one is that the dynamics of emotions extracted using text-based algorithms often do not well reflect the dynamics of self-reported retrospective emotions. Besides, the extracted emotions match self-reported retrospective emotions better at the team-level. Our results also suggest that individual personalities and the team's emotion display norms significantly impact the match/mismatch. Our results should warn the research community about the limitations and challenges of applying text-based emotion recognition tools in SE research.");
        Paper p2=new Paper("Combining Spectrum-Based Fault Localization and Statistical Debugging: An Empirical Study","J. Jiang; R. Wang; Y. Xiong; X. Chen; L. Zhang","Peking University; Peking University; Peking University; Sun Yat-sen University; Peking University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,502,514,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952344","Software engineering, Fault localization, Program debugging",79,"IEEE","IEEE Conferences","Program debugging is a time-consuming task, and researchers have proposed different kinds of automatic fault localization techniques to mitigate the burden of manual debugging. Among these techniques, two popular families are spectrum-based fault localization (SBFL) and statistical debugging (SD), both localizing faults by collecting statistical information at runtime. Though the ideas are similar, the two families have been developed independently and their combinations have not been systematically explored. In this paper we perform a systematical empirical study on the combination of SBFL and SD. We first build a unified model of the two techniques, and systematically explore four types of variations, different predicates, different risk evaluation formulas, different granularities of data collection, and different methods of combining suspicious scores. Our study leads to several findings. First, most of the effectiveness of the combined approach contributed by a simple type of predicates: branch conditions. Second, the risk evaluation formulas of SBFL significantly outperform that of SD. Third, fine-grained data collection significantly outperforms coarse-grained data collection with a little extra execution overhead. Fourth, a linear combination of SBFL and SD predicates outperforms both individual approaches. According to our empirical study, we propose a new fault localization approach, PREDFL (Predicate-based Fault Localization), with the best configuration for each dimension under the unified model. Then, we explore its complementarity to existing techniques by integrating PREDFL with a state-of-the-art fault localization framework. The experimental results show that PREDFL can further improve the effectiveness of state-of-the-art fault localization techniques. More concretely, integrating PREDFL results in an up to 20.8% improvement w.r.t the faults successfully located at Top-1, which reveals that PREDFL complements existing techniques.");
        Paper p3=new Paper("Automating App Review Response Generation","C. Gao; J. Zeng; X. Xia; D. Lo; M. R. Lyu; I. King","The Chinese University of Hong Kong; The Chinese University of Hong Kong; Monash University; Singapore Management University; The Chinese University of Hong Kong; The Chinese University of Hong Kong","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,163,175,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952476","App reviews;response generation;neural machine translation",76,"IEEE","IEEE Conferences","Previous studies showed that replying to a user review usually has a positive effect on the rating that is given by the user to the app. For example, Hassan et al. found that responding to a review increases the chances of a user updating their given rating by up to six times compared to not responding. To alleviate the labor burden in replying to the bulk of user reviews, developers usually adopt a template-based strategy where the templates can express appreciation for using the app or mention the company email address for users to follow up. However, reading a large number of user reviews every day is not an easy task for developers. Thus, there is a need for more automation to help developers respond to user reviews. Addressing the aforementioned need, in this work we propose a novel approach RRGen that automatically generates review responses by learning knowledge relations between reviews and their responses. RRGen explicitly incorporates review attributes, such as user rating and review length, and learns the relations between reviews and corresponding responses in a supervised way from the available training data. Experiments on 58 apps and 309,246 review-response pairs highlight that RRGen outperforms the baselines by at least 67.4% in terms of BLEU-4 (an accuracy measure that is widely used to evaluate dialogue response generation systems). Qualitative analysis also confirms the effectiveness of RRGen in generating relevant and accurate responses.");
        Paper p4=new Paper("DaPanda: Detecting Aggressive Push Notifications in Android Apps","T. Liu; H. Wang; L. Li; G. Bai; Y. Guo; G. Xu","Beijing University of Posts and Telecommunications; Beijing University of Posts and Telecommunications; Monash University; The University of Queensland; Peking University; Beijing University of Posts and Telecommunications","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,66,78,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952509","Push notification;dynamic analysis;advertisement;Android;mobile app",75,"IEEE","IEEE Conferences","Mobile push notifications have been widely used in mobile platforms to deliver all sorts of information to app users. Although it offers great convenience for both app developers and mobile users, this feature was frequently reported to serve malicious and aggressive purposes, such as delivering annoying push notification advertisement. However, to the best of our knowledge, this problem has not been studied by our research community so far. To fill the void, this paper presents the first study to detect aggressive push notifications and further characterize them in the global mobile app ecosystem on a large scale. To this end, we first provide a taxonomy of mobile push notifications and identify the aggressive ones using a crowdsourcing-based method. Then we propose sc DaPanda, a novel hybrid approach, aiming at automatically detecting aggressive push notifications in Android apps. sc DaPanda leverages a guided testing approach to systematically trigger and record push notifications. By instrumenting the Android framework, sc DaPanda further collects all notification-relevant runtime information to flag the aggressive ones. Our experimental results show that sc DaPanda is capable of detecting different types of aggressive push notifications effectively in an automated way. By applying sc DaPanda to 20,000 Android apps from different app markets, it yields over 1,000 aggressive notifications, which have been further confirmed as true positives. Our in-depth analysis further reveals that aggressive notifications are prevalent across different markets and could be manifested in all the phases in the lifecycle of push notifications. It is hence urgent for our community to take actions to detect and mitigate apps involving aggressive push notifications.");
        Paper p5=new Paper("Testing Regex Generalizability And Its Implications: A Large-Scale Many-Language Measurement Study","J. C. Davis; D. Moyer; A. M. Kazerouni; D. Lee","Virginia Tech; Virginia Tech; Virginia Tech; Stony Brook University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,427,439,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952443","Regular expressions;Empirical software engineering;Data driven design;Methods",75,"IEEE","IEEE Conferences","The regular expression (regex) practices of software engineers affect the maintainability, correctness, and security of their software applications. Empirical research has described characteristics like the distribution of regex feature usage, the structural complexity of regexes, and worst-case regex match behaviors. But researchers have not critically examined the methodology they follow to extract regexes, and findings to date are typically generalized from regexes written in only 1-2 programming languages. This is an incomplete foundation. Generalizing existing research depends on validating two hypotheses: (1) Various regex extraction methodologies yield similar results, and (2) Regex characteristics are similar across programming languages. To test these hypotheses, we defined eight regex metrics to capture the dimensions of regex representation, string language diversity, and worst-case match complexity. We report that the two competing regex extraction methodologies yield comparable corpuses, suggesting that simpler regex extraction techniques will still yield sound corpuses. But in comparing regexes across programming languages, we found significant differences in some characteristics by programming language. Our findings have bearing on future empirical methodology, as the programming language should be considered, and generalizability will not be assured. Our measurements on a corpus of 537,806 regexes can guide data-driven designs of a new generation of regex tools and regex engines.");
        Paper p6=new Paper("An Empirical Study Towards Characterizing Deep Learning Development and Deployment Across Different Frameworks and Platforms","Q. Guo; S. Chen; X. Xie; L. Ma; Q. Hu; H. Liu; Y. Liu; J. Zhao; X. Li","Tianjin University; Nanyang Technological University; Nanyang Technological University; Kyushu University; Kyushu University; Tianjin University; Nanyang Technological University; Kyushu University; Tianjin University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,810,822,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952401","Deep learning frameworks;Deep learning platforms;Deep learning deployment;Empirical study",70,"IEEE","IEEE Conferences","Deep Learning (DL) has recently achieved tremendous success. A variety of DL frameworks and platforms play a key role to catalyze such progress. However, the differences in architecture designs and implementations of existing frameworks and platforms bring new challenges for DL software development and deployment. Till now, there is no study on how various mainstream frameworks and platforms influence both DL software development and deployment in practice. To fill this gap, we take the first step towards understanding how the most widely-used DL frameworks and platforms support the DL software development and deployment. We conduct a systematic study on these frameworks and platforms by using two types of DNN architectures and three popular datasets. (1) For development process, we investigate the prediction accuracy under the same runtime training configuration or same model weights/biases. We also study the adversarial robustness of trained models by leveraging the existing adversarial attack techniques. The experimental results show that the computing differences across frameworks could result in an obvious prediction accuracy decline, which should draw the attention of DL developers. (2) For deployment process, we investigate the prediction accuracy and performance (refers to time cost and memory consumption) when the trained models are migrated/quantized from PC to real mobile devices and web browsers. The DL platform study unveils that the migration and quantization still suffer from compatibility and reliability issues. Meanwhile, we find several DL software bugs by using the results as a benchmark. We further validate the results through bug confirmation from stakeholders and industrial positive feedback to highlight the implications of our study. Through our study, we summarize practical guidelines, identify challenges and pinpoint new research directions, such as understanding the characteristics of DL frameworks and platforms, avoiding compatibility and reliability issues, detecting DL software bugs, and reducing time cost and memory consumption towards developing and deploying high quality DL systems effectively.");
        Paper p7=new Paper("Debreach: Mitigating Compression Side Channels via Static Analysis and Transformation","B. Paulsen; C. Sung; P. A.H. Peterson; C. Wang","University of Southern California; University of Southern California; University of Minnesota, Duluth; University of Southern California","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,899,911,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952360","Program Synthesis and Transformations;Automated Defect Repair;Data Privacy;Side Channel",69,"IEEE","IEEE Conferences","Compression is an emerging source of exploitable side-channel leakage that threatens data security, particularly in web applications where compression is indispensable for performance reasons. Current approaches to mitigating compression side channels have drawbacks in that they either degrade compression ratio drastically or require too much effort from developers to be widely adopted. To bridge the gap, we develop Debreach, a static analysis and program transformation based approach to mitigating compression side channels. Debreach consists of two steps. First, it uses taint analysis to soundly identify flows of sensitive data in the program and uses code instrumentation to annotate data before feeding them to the compressor. Second, it enhances the compressor to exploit the freedom to not compress of standard compression protocols, thus removing the dependency between sensitive data and the size of the compressor's output. Since Debreach automatically instruments applications and does not change the compression protocols, it has the advantage of being non-disruptive and compatible with existing systems. We have evaluated Debreach on a set of web server applications written in PHP. Our experiments show that, while ensuring leakage-freedom, Debreach can achieve significantly higher compression performance than state-of-the-art approaches.");
        Paper p8=new Paper("Learning-Guided Network Fuzzing for Testing Cyber-Physical System Defences","Y. Chen; C. M. Poskitt; J. Sun; S. Adepu; F. Zhang","Singapore University of Technology and Design; Singapore University of Technology and Design; Singapore Management University; Singapore University of Technology and Design; Zhejiang University and Alibaba-Zhejiang University Joint Institute of Frontier Technologies","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,962,973,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952193","cyber-physical systems;fuzzing;testing;benchmark generation;machine learning;metaheuristic optimisation",67,"IEEE","IEEE Conferences","The threat of attack faced by cyber-physical systems (CPSs), especially when they play a critical role in automating public infrastructure, has motivated research into a wide variety of attack defence mechanisms. Assessing their effectiveness is challenging, however, as realistic sets of attacks to test them against are not always available. In this paper, we propose smart fuzzing, an automated, machine learning guided technique for systematically finding 'test suites' of CPS network attacks, without requiring any knowledge of the system's control programs or physical processes. Our approach uses predictive machine learning models and metaheuristic search algorithms to guide the fuzzing of actuators so as to drive the CPS into different unsafe physical states. We demonstrate the efficacy of smart fuzzing by implementing it for two real-world CPS testbeds?? water purification plant and a water distribution system??inding attacks that drive them into 27 different unsafe states involving water flow, pressure, and tank levels, including six that were not covered by an established attack benchmark. Finally, we use our approach to test the effectiveness of an invariant-based defence system for the water treatment plant, finding two attacks that were not detected by its physical invariant checks, highlighting a potential weakness that could be exploited in certain conditions.");
        Paper p9=new Paper("Assessing the Generalizability of Code2vec Token Embeddings","H. J. Kang; T. F. Bissyand?; D. Lo","Singapore Management University; University of Luxembourg, Luxembourg; Singapore Management University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,1,12,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952475","Code Embeddings;Distributed Representations;Big Code",65,"IEEE","IEEE Conferences","Many Natural Language Processing (NLP) tasks, such as sentiment analysis or syntactic parsing, have benefited from the development of word embedding models. In particular, regardless of the training algorithms, the learned embeddings have often been shown to be generalizable to different NLP tasks. In contrast, despite recent momentum on word embeddings for source code, the literature lacks evidence of their generalizability beyond the example task they have been trained for. In this experience paper, we identify 3 potential downstream tasks, namely code comments generation, code authorship identification, and code clones detection, that source code token embedding models can be applied to. We empirically assess a recently proposed code token embedding model, namely code2vec's token embeddings. Code2vec was trained on the task of predicting method names, and while there is potential for using the vectors it learns on other tasks, it has not been explored in literature. Therefore, we fill this gap by focusing on its generalizability for the tasks we have identified. Eventually, we show that source code token embeddings cannot be readily leveraged for the downstream tasks. Our experiments even show that our attempts to use them do not result in any improvements over less sophisticated methods. We call for more research into effective and general use of code embeddings.");
        Paper p10=new Paper("MARBLE: Mining for Boilerplate Code to Identify API Usability Problems","D. Nam; A. Horvath; A. Macvean; B. Myers; B. Vasilescu","Carnegie Mellon University; Carnegie Mellon University; Google; Carnegie Mellon University; Carnegie Mellon University","2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)",2019,615,627,"https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8952239","Boilerplate Code;API Usability;Repository Mining",65,"IEEE","IEEE Conferences","Designing usable APIs is critical to developers' productivity and software quality, but is quite difficult. One of the challenges is that anticipating API usability barriers and real-world usage is difficult, due to a lack of automated approaches to mine usability data at scale. In this paper, we focus on one particular grievance that developers repeatedly express in online discussions about APIs: \"boilerplate code.\" We investigate what properties make code count as boilerplate, the reasons for boilerplate, and how programmers can reduce the need for it. We then present MARBLE, a novel approach to automatically mine boilerplate code candidates from API client code repositories. MARBLE adapts existing techniques, including an API usage mining algorithm, an AST comparison algorithm, and a graph partitioning algorithm. We evaluate MARBLE with 13 Java APIs, and show that our approach successfully identifies both already-known and new API-related boilerplate code instances.");

        expect.add(p1);
        expect.add(p2);
        expect.add(p3);
        expect.add(p4);
        expect.add(p5);
        expect.add(p6);
        expect.add(p7);
        expect.add(p8);
        expect.add(p9);
        expect.add(p10);

        assertEquals(autal.size(),expect.size());
        for(int i=0;i<autal.size();i++){
            judgePaper(autal.get(i),expect.get(i));
        }

         */
    }

    @Test
    void getAuthorByConferenceName() {
        ConferPicMapper conferPicMapper=mock(ConferPicMapper.class);
        List<AuthorPic> expect=new ArrayList<>();

        when(conferPicMapper.selectAuthorByConferName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)")).thenReturn(expect);
        GetConferPic getConferPic=new GetConferPic();
        try{
            Field declaredField = GetConferPic.class.getDeclaredField("conferPicMapper");
            declaredField.setAccessible(true);
            ReflectionUtils.setField(declaredField,getConferPic,conferPicMapper);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(getConferPic.getAuthorByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)").equals(expect));

        /*List<AuthorPic> autal=getConferPic.getAuthorByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
        assertEquals(autal.size(),50);
        AuthorPic a2=new AuthorPic("Y. Liu","Nanyang Technological University, Singapore",7,345);
        AuthorPic a3=new AuthorPic("S. Apel","Univ. of Passau, Passau, Germany",7,262);
        AuthorPic a1=new AuthorPic("J. Sun","Singapore University of Technology and Design, Singapore",8,294);
        AuthorPic a4=new AuthorPic("Y. Xiong","Key Lab. of High Confidence Software Technol., Peking Univ., Beijing, China",6,267);
        AuthorPic a5=new AuthorPic("L. Zhang","Key Lab. of High Confidence Software Technol., Peking Univ., Beijing, China",6,255);

        expect.add(a1);
        expect.add(a2);
        expect.add(a3);
        expect.add(a4);
        expect.add(a5);
        for(int i=0;i<3;i++)
            judgeAuthor(autal.get(i),expect.get(i));

         */
    }

    void judgePaper(Paper autal,Paper expect){
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

    void judgeAuthor(AuthorPic autal,AuthorPic expect){

        assertEquals(autal.getPapers(),expect.getPapers());
        assertEquals(autal.getRefs(),expect.getRefs());
    }

}