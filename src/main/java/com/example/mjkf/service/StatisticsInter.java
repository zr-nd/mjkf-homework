package com.example.mjkf.service;

import com.example.mjkf.po.Author;
import com.example.mjkf.po.Conference;
import com.example.mjkf.po.Paper;

import java.util.List;

public interface StatisticsInter {

    List<Paper> statisticsPaper();

    List<Conference> statisticConference();

    List<Author> getMostAuthor();

}
