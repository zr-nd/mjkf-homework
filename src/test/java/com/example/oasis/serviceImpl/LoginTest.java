package com.example.oasis.serviceImpl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LoginTest {
    @Autowired
    private Login login;

    @Test
    public void hasUserTest1(){
        String username = "admin";
        String password1 = "123123";
        boolean result = login.hasUser(username,password1);
        assertEquals(result,true);
    }

    @Test
    public void hasUserTest2(){
        String username = "admin";
        String password2 = "123456";
        boolean result2 = login.hasUser(username,password2);
        assertEquals(result2,false);
    }



}
