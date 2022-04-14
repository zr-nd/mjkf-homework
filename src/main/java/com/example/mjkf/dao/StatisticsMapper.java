package com.example.mjkf.dao;

import com.example.mjkf.po.Author;
import com.example.mjkf.po.Conference;
import com.example.mjkf.po.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StatisticsMapper {
    List<Paper> selectPaperByRefer();

    List<Conference> selectConference();

    List<Author> getMostAuthor();

    /*List<AuthorPic> getMostAuthorPic();

    AuthorPic getAuthorPicByName(String author,String affiliation);

    List<Paper> getPapersByAuthorPic(String author,String affiliation);*/
}
