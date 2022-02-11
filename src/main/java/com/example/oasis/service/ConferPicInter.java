package com.example.oasis.service;

import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.ConferPic;
import com.example.oasis.po.Paper;

import java.util.List;

public interface ConferPicInter {

    List<ConferPic> getConferPic();

    List<Paper> getPaperByConferenceName(String name);

    List<AuthorPic> getAuthorByConferenceName(String name);
}
