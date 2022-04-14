package com.example.mjkf.controller;


import com.example.mjkf.po.Author;
import com.example.mjkf.po.Conference;
import com.example.mjkf.po.Paper;
import com.example.mjkf.service.StatisticsInter;
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
