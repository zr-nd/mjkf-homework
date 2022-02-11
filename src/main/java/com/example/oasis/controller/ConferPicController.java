package com.example.oasis.controller;


import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.ConferPic;
import com.example.oasis.po.Paper;

import com.example.oasis.service.ConferPicInter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@MapperScan("com.example.oasis.dao")
@ComponentScan(basePackages = {"com.example.oasis.service","com.example.oasis.dao"})
public class ConferPicController {

    @Autowired
    private ConferPicInter conferPicInter;

    public ConferPicController(ConferPicInter conferPicInter){this.conferPicInter=conferPicInter;}

    @RequestMapping(value = {"/conferPic"},method = RequestMethod.POST)
    @ResponseBody
    public List<ConferPic> generateConferPic(){
        List<ConferPic> conferPicList = conferPicInter.getConferPic();
        //System.out.println("generate Confer Pic");
        return conferPicList;
    }

    //页面跳转
    @RequestMapping("/conferPicPage")
    public String conferPicJump(){
        return "conferPicPage.html";
    }

    @RequestMapping(value = "/getPaperByConferName",method = RequestMethod.POST)
    @ResponseBody
    public List<Paper> getPaperByConferName(@RequestBody(required=false)String name){
        //System.out.println("get paper By confer Name");
        List<Paper> result = conferPicInter.getPaperByConferenceName(name.substring(1,name.length()-1));
        //System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "/getAuthorByConferName",method = RequestMethod.POST)
    @ResponseBody
    public List<AuthorPic> getAuthorPicByConferName(@RequestBody(required=false) String name){
        System.out.println("get Auhtors ! ");
        List<AuthorPic> result = conferPicInter.getAuthorByConferenceName(name.substring(1,name.length()-1));
        System.out.println(result.size());
        return result;
    }

}
