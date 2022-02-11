package com.example.oasis.controller;

import com.alibaba.fastjson.JSON;
import com.example.oasis.service.AuthorPicInter;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class AuthorPicControllerTest {

    @Test
    void getMostAuthorPic() throws Exception{
        AuthorPicInter authorPicInter=mock(AuthorPicInter.class);
        AuthorPicController authorPicController=new AuthorPicController(authorPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authorPicController).build();

        mockMvc.perform(post("/authorPic")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(authorPicInter).getMostAuthorPic();
    }

    @Test
    void getAuthorPicByName() throws Exception{
        AuthorPicInter authorPicInter=mock(AuthorPicInter.class);
        AuthorPicController authorPicController=new AuthorPicController(authorPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authorPicController).build();
        Map<String,String> map=new HashMap<String, String>();

        map.put("affiliation","Google, USA");
        map.put("author","J. Penix");
        String jsonStr = JSON.toJSONString(map);
        mockMvc.perform(post("/authorPic/byName")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(authorPicInter).getAuthorPicByName("J. Penix","Google, USA");
    }

    @Test
    void getAuthorPicByNameAndTitle() throws Exception{
        AuthorPicInter authorPicInter=mock(AuthorPicInter.class);
        AuthorPicController authorPicController=new AuthorPicController(authorPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authorPicController).build();
        Map<String,String> map=new HashMap<String, String>();

        map.put("title","Retrieve and Refine: Exemplar-Based Neural Comment Generation");
        map.put("author","B. Wei");
        String jsonStr = JSON.toJSONString(map);
        mockMvc.perform(post("/authorPic/byNameAndTitle")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(authorPicInter).getAuthorPicByNameAndTitle("B. Wei","Retrieve and Refine: Exemplar-Based Neural Comment Generation");
    }

    @Test
    void getPapersByAuthorPic() throws Exception{
        AuthorPicInter authorPicInter=mock(AuthorPicInter.class);
        AuthorPicController authorPicController=new AuthorPicController(authorPicInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authorPicController).build();
        Map<String,String> map=new HashMap<String, String>();
        map.put("affiliation","Peking University");
        map.put("author","B. Wei");
        String jsonStr = JSON.toJSONString(map);

        mockMvc.perform(post("/author/authorPapers")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(authorPicInter).getPapersByAuthorPic("B. Wei","Peking University");
    }


}