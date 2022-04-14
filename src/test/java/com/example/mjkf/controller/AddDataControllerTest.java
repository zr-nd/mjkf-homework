package com.example.mjkf.controller;


import com.example.mjkf.service.AddDataInter;
import org.junit.Test;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



public class AddDataControllerTest {

    @Test
    public void updataTest() throws Exception{


        AddDataInter addDataInter=mock(AddDataInter.class);
        AddDataController controller =new AddDataController(addDataInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        File file=new File("E:\\test.xlsx");
        file.createNewFile();
        mockMvc.perform(post("/updata")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"E:\\test.xlsx\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(addDataInter).addData("E:\\test.xlsx");
        File file1=new File("E:\\text.xlsx");
        mockMvc.perform(post("/updata")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("\"E:\\text.xlsx\""))
                .andExpect(MockMvcResultMatchers.content().string("-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
