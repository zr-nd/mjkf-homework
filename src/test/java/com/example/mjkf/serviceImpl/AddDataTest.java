package com.example.mjkf.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddDataTest {
    @Autowired
    private AddDataImpl addData;

    @Test
    public void addDataTest(){
        int result=-2;
        int result1=-2;
        try {
            result = addData.addData("E:\\test.xlsx"); //测试文件要随时改，情况不一定
            result1= addData.addData(null);
        }catch(Exception e){}
        finally {
            assertEquals(result, -1);
            assertEquals(result1, -1);
        }
    }
}
