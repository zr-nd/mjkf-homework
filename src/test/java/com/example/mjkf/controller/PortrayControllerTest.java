package com.example.mjkf.controller;

import com.example.mjkf.service.PortrayInter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PortrayControllerTest {

    @Test
    void getKeywordPortrayTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();

        mockMvc.perform(post("/researchPortray")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"Adaptation\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(portrayInter).getKeywordPortray("Adaptation");
    }

    @Test
    void getTopPaerinFieldTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();

        mockMvc.perform(post("/topPaperinField")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"BSD\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(portrayInter).selectTopPaperinField("BSD");
    }

    @Test
    void selectPopFieldTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();

        mockMvc.perform(post("/popField")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(portrayInter).selectPopField();
    }

    @Test
    void getTopAuthorInFieldTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();

        mockMvc.perform(post("/topAuthorinField")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"Testing\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(portrayInter).selectTopAuthorInField("Testing");
    }

    @Test
    void getTopAffiliationInFieldTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();

        mockMvc.perform(post("/topAffiliationinField")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"Testing\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(portrayInter).selectTopAffiliationInField("Testing");
    }

    @Test
    void researchFieldJumpPageTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();
        mockMvc.perform(post("/researchField")).andExpect(view().name("researchField.html"));
    }

    @Test
    void researchFieldBackJumpPageTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();
        mockMvc.perform(post("/searchPage")).andExpect(view().name("searchPage.html"));
    }

    @Test
    void fieldToDetailJumpPageTest() throws Exception{
        PortrayInter portrayInter=mock(PortrayInter.class);
        PortrayController portrayController=new PortrayController(portrayInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(portrayController).build();
        mockMvc.perform(post("/detailByTitle")).andExpect(view().name("detailByTitle.html"));
    }
}