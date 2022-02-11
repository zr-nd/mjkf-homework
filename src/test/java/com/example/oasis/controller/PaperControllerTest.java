package com.example.oasis.controller;


import com.example.oasis.po.Links;
import com.example.oasis.po.Nodes;
import com.example.oasis.service.PortrayInter;
import com.example.oasis.service.SearchInter;
import com.example.oasis.vo.LinkVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


class PaperControllerTest {

    @Test
    void getPaper() throws Exception {

        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //单值查询测试
        mockMvc.perform(post("/index")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"B. Wei\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getListPaper("B. Wei");

        //组合查询测试
        mockMvc.perform(post("/index")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"X. Chen&Tsinghua\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getListPaperByCombina("X. Chen&Tsinghua");

        //年份区间查询测试
        mockMvc.perform(post("/index")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"2013-2015\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getListPaper("2013-2015");
    }

    @Test
    void indexTest() throws Exception {
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/")).andExpect(view().name("searchPage.html"));
    }

    @Test
    void searchPageJumpPageTest()throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/searchRes")).andExpect(view().name("searchRes.html"));
    }

    @Test
    void deatilJumpPageTest()throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/detail")).andExpect(view().name("detail.html"));
    }

    @Test
    void affiliationDetailJumpPageTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/affiliationDetail")).andExpect(view().name("affiliation.html"));
    }

    @Test
    void authorJumpPageTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/authorDetail")).andExpect(view().name("authorDetail.html"));
    }

    @Test
    void affiliationDetailTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/affiliation")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"CMU\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getAffiliation("CMU");
    }

    @Test
    void affiliationRankingTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/index1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getTopAffiliation();
    }

    @Test
    void getAffiliationFieldTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/affiliationField")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"Google\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getAffiliationField("Google");
    }

    @Test
    void selectLinksTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/links")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getNodesLinks();
    }

    @Test
    void selectLinksAffiliationsTest() throws Exception{
        SearchInter searchInter=mock(SearchInter.class);
        PaperController controller =new PaperController(searchInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/linksAffiliation")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(searchInter).getAffiliationNodesAndLinks();
    }
}


