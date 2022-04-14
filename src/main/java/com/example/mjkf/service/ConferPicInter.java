package com.example.mjkf.service;

import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.ConferPic;
import com.example.mjkf.po.Paper;

import java.util.List;

public interface ConferPicInter {

    List<ConferPic> getConferPic();

    List<Paper> getPaperByConferenceName(String name);

    List<AuthorPic> getAuthorByConferenceName(String name);
}
