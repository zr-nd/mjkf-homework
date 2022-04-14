package com.example.mjkf.controller;

import com.example.mjkf.po.Keyword;
import com.example.mjkf.po.TopAffiliation;
import com.example.mjkf.po.TopAuthor;
import com.example.mjkf.po.TopField;
import com.example.mjkf.service.PortrayInter;
import com.example.mjkf.vo.PaperVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@MapperScan("com.example.mjkf.dao")
@ComponentScan(basePackages = {"com.example.mjkf.service", "com.example.mjkf.dao"})
public class PortrayController {
    @Autowired
    private PortrayInter portrayInter;

    public PortrayController(PortrayInter portrayInter){this.portrayInter=portrayInter;}

    @RequestMapping(value = {"/researchPortray"},method = RequestMethod.POST)
    @ResponseBody
    public Keyword getKeywordPortray(@RequestBody(required=false) String keyword){
        String k=keyword.substring(1,keyword.length()-1);
        return portrayInter.getKeywordPortray(k);
    }

    @RequestMapping(value = {"/topPaperinField"},method = RequestMethod.POST)
    @ResponseBody
    public List<PaperVO> getTopPaerinField(@RequestBody(required=false) String keyword){
        String k=keyword.substring(1,keyword.length()-1);
        return portrayInter.selectTopPaperinField(k);
    }

    @RequestMapping(value = {"/topAuthorinField"},method = RequestMethod.POST)
    @ResponseBody
    public List<TopAuthor> getTopAuthorInField(@RequestBody(required=false) String keyword){
        String k=keyword.substring(1,keyword.length()-1);
        return portrayInter.selectTopAuthorInField(k);
    }

    @RequestMapping(value = {"/topAffiliationinField"},method = RequestMethod.POST)
    @ResponseBody
    public List<TopAffiliation> getTopAffiliationInField(@RequestBody(required=false) String keyword){
        String k=keyword.substring(1,keyword.length()-1);
        return portrayInter.selectTopAffiliationInField(k);
    }

    @RequestMapping(value = {"/popField"},method = RequestMethod.POST)
    @ResponseBody
    public List<TopField> selectPopField(){return portrayInter.selectPopField();}

    @RequestMapping("/researchField")
    public String researchFieldJumpPage(){
        return "researchField.html";
    }

    @RequestMapping("/searchPage")
    public String researchFieldBackJumpPage(){return "searchPage.html";}

    @RequestMapping("/detailByTitle")
    public String fieldToDetailJumpPage(){return "detailByTitle.html";}

}
