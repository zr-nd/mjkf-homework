package com.example.oasis.dao;

import com.example.oasis.po.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface AddDataMapper {
    //
    List<Paper> selectByTitleAndAuthors(String title, String authors);

    void insertPaper(String title, String authors, String affiliations, String publicTitle,
                     int year, int sp, int ep, String abs,
                     String pdf, String keyWord, int ref, String pub, String docId);
}
