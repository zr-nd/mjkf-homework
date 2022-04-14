package com.example.mjkf.controller;

import com.example.mjkf.po.*;
import com.example.mjkf.service.PortrayInter;
import com.example.mjkf.service.SearchInter;
import com.example.mjkf.vo.AffiliationVO;
import com.example.mjkf.vo.LinkVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@MapperScan("com.example.mjkf.dao")
@ComponentScan(basePackages = {"com.example.mjkf.serviceImpl", "com.example.mjkf.dao"})

public class PaperController {
    @Autowired
    private SearchInter searchInter;
    @Autowired
    private PortrayInter portrayInter;

    public PaperController() {
    }
    public PaperController(SearchInter searchInter){
        this.searchInter=searchInter;
    }

    @RequestMapping(value = {"/index"},method = RequestMethod.POST)
    @ResponseBody
    public List<Paper> getPaper(@RequestBody(required=false) String searchField){
        List<Paper> paperlist;
        if(searchField.contains("&")){
            paperlist = searchInter.getListPaperByCombina(searchField.substring(1, searchField.length() - 1));
        }
        else {
            paperlist = searchInter.getListPaper(searchField.substring(1, searchField.length() - 1));
        }
        return paperlist;
    }


    @RequestMapping()
    public String index(){

        return "searchPage.html";
    }

    @RequestMapping("/searchRes")
    public String searchPageJumpPage(){
        return "searchRes.html";
    }

    @RequestMapping("/detail")
    public String detailJumpPage(){
        return "detail.html";
    }

    @RequestMapping(value = {"/affiliation"},method = RequestMethod.POST)
    @ResponseBody
    public AffiliationVO affiliationDetail(@RequestBody(required=false) String name){
        name=name.substring(1,name.length()-1);
        return searchInter.getAffiliation(name);
    }

    @RequestMapping(value = {"/index1"},method = RequestMethod.POST)
    @ResponseBody
    public List<TopAffiliation> affiliationRanking(){
        return searchInter.getTopAffiliation();
    }

    @RequestMapping("/affiliationDetail")
    public String affiliationDetailJumpPage(){
        return "affiliation.html";
    }

    @RequestMapping("/authorDetail")
    public String authorJumpPage(){return "authorDetail.html";}

    @RequestMapping("/link")
    public String linksJumpPage(){return "link.html";}

    @RequestMapping("/link1")
    public String linkJumpPage(){return "link1.html";}

    @RequestMapping("/hotPapers")
    public String hotPapersJumpPage(){return "hotPapers.html";}

    @RequestMapping("/hotFields")
    public String hotFieldsYumpPage(){return "hotFields.html";}


    @RequestMapping(value = "/links",method = RequestMethod.GET)
    @ResponseBody
    public LinkVO selectLinks(){

        return searchInter.getNodesLinks();
    }

    @RequestMapping(value = "/linksAffiliation",method = RequestMethod.GET)
    @ResponseBody
    public LinkVO selectLinksAffiliation(){
        return searchInter.getAffiliationNodesAndLinks();
    }

    //获得机构的研究方向
    @RequestMapping(value = "/affiliationField",method = RequestMethod.POST)
    @ResponseBody
    public List<AffiliationField> getAffiliationField(@RequestBody(required=false) String name){
        name=name.substring(1,name.length()-1);
        return searchInter.getAffiliationField(name);
    }

    @RequestMapping("/rank")
    public String RankJumpPage(){return "rank.html";}

}

