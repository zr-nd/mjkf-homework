package com.example.mjkf.controller;

import com.example.mjkf.po.Affiliation;
import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.PublicTrend;
import com.example.mjkf.service.RankInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RankController {
    @Autowired
    private RankInter rankInter;

    public RankController(RankInter rankInter){this.rankInter=rankInter;}

    @RequestMapping(value = {"/rankAffiliation"},method = RequestMethod.POST)
    @ResponseBody
    public List<Affiliation> rankAffiliation(@RequestBody Map<String,String> data){
//        String country=data.get("country");
        String publisher=data.get("publisher");
        int startYear=Integer.parseInt(data.get("startYear"));
        int endYear=Integer.parseInt(data.get("endYear"));
        String field=data.get("field");
        String conference=data.get("conference");
        String attribute=data.get("attribute");
        List<Affiliation> res=new ArrayList<>();
        switch (attribute){
            case "papers":
                res=rankInter.rankAffiliationByPapers(publisher,startYear,endYear,field,conference);break;
            case "refs":
                res=rankInter.rankAffiliationByRefs(publisher,startYear,endYear,field,conference);break;
            //case "authors":
             //   res=rankInter.rankAffiliationByAuthors(country,startYear,endYear,field,conference);break;
            default:
                break;
        }
        return res;
    }

    @RequestMapping(value = {"/rankAuthor"},method = RequestMethod.POST)
    @ResponseBody
    public List<Affiliation> rankAuthor(@RequestBody Map<String,String> data){
        String publisher=data.get("publisher");
        int startYear=Integer.parseInt(data.get("startYear"));
        int endYear=Integer.parseInt(data.get("endYear"));
        String field=data.get("field");
        String conference=data.get("conference");
        String attribute=data.get("attribute");
        List<Affiliation> res=new ArrayList<>();

        switch (attribute){
            case "papers":
                res=rankInter.rankAuthorByPapers(publisher,startYear,endYear,field,conference);break;
            case "refs":
                res=rankInter.rankAuthorByRefs(publisher,startYear,endYear,field,conference);break;
            default:
                break;
        }
        return res;
    }

    @RequestMapping(value = {"/publicTrend1"},method = RequestMethod.POST)
    @ResponseBody
    public List<PublicTrend> getPublicTrend1(@RequestBody Map<String,String> data){
        String name=data.get("name");
        int startYear=Integer.parseInt(data.get("startYear"));
        int endYear=Integer.parseInt(data.get("endYear"));
        return rankInter.getPublicTrend1(name,startYear,endYear);
    }

    @RequestMapping(value = {"/publicTrend2"},method = RequestMethod.POST)
    @ResponseBody
    public List<PublicTrend> getPublicTrend2(@RequestBody Map<String,String> data){
        String name=data.get("name");
        String affiliation=data.get("affiliation");
        int startYear=Integer.parseInt(data.get("startYear"));
        int endYear=Integer.parseInt(data.get("endYear"));
        return rankInter.getPublicTrend2(name,affiliation,startYear,endYear);
    }
}
