package com.example.oasis.controller;

import com.example.oasis.service.ConferPicInter;
import com.example.oasis.service.PortrayInter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class ConferPicControllerTest {

    @Test
    void generateConferPicTest() throws Exception{
        ConferPicInter conferPicInter=mock( ConferPicInter.class);
        ConferPicController conferPicController=new ConferPicController(conferPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(conferPicController).build();

        mockMvc.perform(post("/conferPic")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(conferPicInter).getConferPic();
    }

    @Test
    void conferPicJumpTest() throws Exception{
        ConferPicInter conferPicInter=mock( ConferPicInter.class);
        ConferPicController conferPicController=new ConferPicController(conferPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(conferPicController).build();
        mockMvc.perform(post("/conferPicPage")).andExpect(view().name("conferPicPage.html"));
    }

    @Test
    void getPaperByConferNameTest() throws Exception{
        ConferPicInter conferPicInter=mock( ConferPicInter.class);
        ConferPicController conferPicController=new ConferPicController(conferPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(conferPicController).build();

        mockMvc.perform(post("/getPaperByConferName")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(conferPicInter).getPaperByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
    }

    @Test
    void getAuthorPicByConferNameTest() throws Exception{
        ConferPicInter conferPicInter=mock( ConferPicInter.class);
        ConferPicController conferPicController=new ConferPicController(conferPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(conferPicController).build();

        mockMvc.perform(post("/getAuthorByConferName")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(conferPicInter).getAuthorByConferenceName("2019 34th IEEE/ACM International Conference on Automated Software Engineering (ASE)");
    }
}