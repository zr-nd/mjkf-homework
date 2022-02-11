package com.example.oasis.controller;

import com.example.oasis.service.StatisticsInter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class StatisticsControllerTest {
    @Test
    public void statisticPaperTest()throws Exception{
        StatisticsInter statisticsInter = mock(StatisticsInter.class);
        StatisticsController statisticsController = new StatisticsController(statisticsInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();

        mockMvc.perform(post("/referenceCount")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(statisticsInter).statisticsPaper();
    }

    @Test
    public void statisticConferenceTest()throws Exception{
        StatisticsInter statisticsInter = mock(StatisticsInter.class);
        StatisticsController statisticsController = new StatisticsController(statisticsInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();

        mockMvc.perform(post("/conference")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(statisticsInter).statisticConference();
    }

    @Test
    public void getMostAuthorTest()throws Exception{
        StatisticsInter statisticsInter = mock(StatisticsInter.class);
        StatisticsController statisticsController = new StatisticsController(statisticsInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();

        mockMvc.perform(post("/author")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(statisticsInter).getMostAuthor();
    }


}
