package com.example.mjkf.controller;

import com.alibaba.fastjson.JSON;
import com.example.mjkf.service.RankInter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class RankControllerTest {


    @Test
    void rankAffiliation() throws Exception{
        RankInter rankInter=mock(RankInter.class);
        RankController rankController=new RankController(rankInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rankController).build();
        Map<String,String> map=new HashMap<String, String>();
        map.put("publisher","All");
        map.put("startYear","2010");
        map.put("endYear","2019");
        map.put("field","All");
        map.put("conference","ASE;");
        map.put("attribute","papers");
        String jsonStr = JSON.toJSONString(map);

        mockMvc.perform(post("/rankAffiliation")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).rankAffiliationByPapers("All",2010,2019,"All","ASE;");

        Map<String,String> map2=new HashMap<String, String>();
        map2.put("publisher","All");
        map2.put("startYear","2010");
        map2.put("endYear","2019");
        map2.put("field","All");
        map2.put("conference","ASE;");
        map2.put("attribute","refs");
        String jsonStr2 = JSON.toJSONString(map2);

        mockMvc.perform(post("/rankAffiliation")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).rankAffiliationByRefs("All",2010,2019,"All","ASE;");
    }

    @Test
    void rankAuthor() throws Exception{
        RankInter rankInter=mock(RankInter.class);
        RankController rankController=new RankController(rankInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rankController).build();
        Map<String,String> map=new HashMap<String, String>();
        map.put("publisher","All");
        map.put("startYear","2010");
        map.put("endYear","2019");
        map.put("field","All");
        map.put("conference","ASE;");
        map.put("attribute","papers");
        String jsonStr = JSON.toJSONString(map);

        mockMvc.perform(post("/rankAuthor")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).rankAuthorByPapers("All",2010,2019,"All","ASE;");

        Map<String,String> map2=new HashMap<String, String>();
        map2.put("publisher","All");
        map2.put("startYear","2010");
        map2.put("endYear","2019");
        map2.put("field","All");
        map2.put("conference","ASE;");
        map2.put("attribute","refs");
        String jsonStr2 = JSON.toJSONString(map2);

        mockMvc.perform(post("/rankAuthor")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).rankAuthorByRefs("All",2010,2019,"All","ASE;");
    }

    @Test
    void getPublicTrend1() throws Exception{
        RankInter rankInter=mock(RankInter.class);
        RankController rankController=new RankController(rankInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rankController).build();
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","Peking University");
        map.put("startYear","2019");
        map.put("endYear","2019");
        String jsonStr=JSON.toJSONString(map);

        mockMvc.perform(post("/publicTrend1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).getPublicTrend1("Peking University",2019,2019);
    }

    @Test
    void getPublicTrend2() throws Exception{
        RankInter rankInter=mock(RankInter.class);
        RankController rankController=new RankController(rankInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rankController).build();
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","Y. Liu");
        map.put("affiliation","Nanyang Technological University, Singapore");
        map.put("startYear","2017");
        map.put("endYear","2019");
        String jsonStr=JSON.toJSONString(map);

        mockMvc.perform(post("/publicTrend2")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(rankInter).getPublicTrend2("Y. Liu","Nanyang Technological University, Singapore",2017,2019);
    }
}