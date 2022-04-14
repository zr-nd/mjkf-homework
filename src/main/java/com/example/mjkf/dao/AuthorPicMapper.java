package com.example.mjkf.dao;

import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthorPicMapper {
    List<AuthorPic> getMostAuthorPic();

    AuthorPic getAuthorPicByName(@Param("author")String author,@Param("affiliation") String affiliation);

    AuthorPic getAuthorPicByNameAndTitle(@Param("author") String author,@Param("title") String title);

    List<Paper> getPapersByAuthorPic(@Param("author") String author, @Param("affiliation") String affiliation);

}
