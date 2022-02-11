package com.example.oasis.controller;

import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.Paper;
import com.example.oasis.service.AuthorPicInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorPicController {

    @Autowired
    private AuthorPicInter authorPicInter;

    public AuthorPicController(AuthorPicInter authorPicInter){this.authorPicInter=authorPicInter;}

    @RequestMapping(value = {"/authorPic"},method = RequestMethod.POST)
    @ResponseBody
    public List<AuthorPic> getMostAuthorPic(){return authorPicInter.getMostAuthorPic();}

    /*@RequestMapping(value={"/authorPic/byName"},method=RequestMethod.GET)
    public AuthorPic getAuthorPicByName(@RequestParam String author, @RequestParam String affiliation){
        return authorPicInter.getAuthorPicByName(author,affiliation);
    }*/

    @RequestMapping(value={"/authorPic/byName"},method=RequestMethod.POST)
    @ResponseBody
    public AuthorPic getAuthorPicByName(@RequestBody Map<String, String> res){
        String author=res.get("author");
        String affiliation=res.get("affiliation");
        return authorPicInter.getAuthorPicByName(author,affiliation);
    }

    @RequestMapping(value={"/authorPic/byNameAndTitle"},method=RequestMethod.POST)
    @ResponseBody
    public AuthorPic getAuthorPicByNameAndTitle(@RequestBody Map<String, String> res){
        String author=res.get("author");
        String title=res.get("title");
        return authorPicInter.getAuthorPicByNameAndTitle(author,title);
    }

    @RequestMapping(value={"/author/authorPapers"},method = RequestMethod.POST)
    @ResponseBody
    public List<Paper> getPapersByAuthorPic(@RequestBody Map<String,String> res){
        String author=res.get("author");
        String affiliation=res.get("affiliation");
        //System.out.println(author+" aaa "+affiliation);
        return authorPicInter.getPapersByAuthorPic(author,affiliation);
    }
}
