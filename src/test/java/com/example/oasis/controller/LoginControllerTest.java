package com.example.oasis.controller;


import com.alibaba.fastjson.JSON;
import com.example.oasis.serviceImpl.Login;
import com.example.oasis.vo.UserVO;
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



public class LoginControllerTest {

    @Test
    public void  getUserPwdTest() throws Exception{
        Login loginInter = mock(Login.class);
        LoginController loginController = new LoginController(loginInter);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        UserVO user = new UserVO();
        user.setUsername("admin");
        user.setPassword("123123");
        String jsonStr = JSON.toJSONString(user);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(loginInter).hasUser("admin","123123");
    }
}
