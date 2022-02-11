package com.example.oasis.service;

import com.example.oasis.po.Author;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.Conference;
import com.example.oasis.po.Paper;

import java.util.List;

public interface StatisticsInter {

    List<Paper> statisticsPaper();

    List<Conference> statisticConference();

    List<Author> getMostAuthor();

}
