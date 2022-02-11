package com.example.oasis.serviceImpl;

import com.example.oasis.dao.StatisticsMapper;
import com.example.oasis.po.Author;
import com.example.oasis.po.Conference;
import com.example.oasis.po.Paper;
import com.example.oasis.service.StatisticsInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Statistics implements StatisticsInter {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public List<Paper> statisticsPaper(){
        return statisticsMapper.selectPaperByRefer();
    }

    public List<Conference> statisticConference(){
        return statisticsMapper.selectConference();
    }


    public List<Author> getMostAuthor() {
        return statisticsMapper.getMostAuthor();
    }

    /*public List<AuthorPicImpl> getMostAuthorPic(){
        return statisticsMapper.getMostAuthorPic();
    }//获得作者画像

    public AuthorPicImpl getAuthorPicByName(String author,String affiliation){
        return statisticsMapper.getAuthorPicByName(author,affiliation);
    }

    public List<Paper> getPapersByAuthorPic(String author, String affiliation) {
        return statisticsMapper.getPapersByAuthorPic(author,affiliation);
    }//根据作者来获得他的文章
    */
}
