package com.example.oasis.controller;


import com.example.oasis.po.Author;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.Conference;
import com.example.oasis.po.Paper;
import com.example.oasis.service.StatisticsInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsInter statisticsInter;

    public StatisticsController(StatisticsInter statisticsInter){this.statisticsInter = statisticsInter;}

    @RequestMapping(value = {"/referenceCount"},method = RequestMethod.POST)
    @ResponseBody
    public List<Paper> statisticPaper(){
        return statisticsInter.statisticsPaper();
    }

    @RequestMapping(value = {"/conference"},method = RequestMethod.POST)
    @ResponseBody
    public List<Conference> statisticConference(){
        return statisticsInter.statisticConference();
    }

    @RequestMapping(value = {"/author"},method = RequestMethod.POST)
    @ResponseBody
    public List<Author> getMostAuthor(){return statisticsInter.getMostAuthor();}

}
