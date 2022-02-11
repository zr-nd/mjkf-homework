package com.example.oasis.controller;


import com.example.oasis.service.AddDataInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
public class AddDataController {


    @Autowired
    private AddDataInter addDataInter;

    public AddDataController(AddDataInter addDataInter){
        this.addDataInter = addDataInter;
    }

    @RequestMapping(value = {"/updata"},method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody(required = false) String filePath){ //处理.xlsx 文件 必须给全文件名路径
        System.out.println(filePath);
        String new1 = filePath;
        File file = new File(new1.substring(1, new1.length() - 1));
        if(!file.exists()){
            return -1;
        }else {
            int result = addDataInter.addData(new1.substring(1, new1.length() - 1));
            return result;
        }
    }

}
