package com.example.mjkf.service;

import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.Paper;

import java.util.List;

public interface AuthorPicInter {
    List<AuthorPic> getMostAuthorPic();

    AuthorPic getAuthorPicByName(String author,String affiliation);

    AuthorPic getAuthorPicByNameAndTitle(String author,String title);

    List<Paper> getPapersByAuthorPic(String author, String affiliation);
}
