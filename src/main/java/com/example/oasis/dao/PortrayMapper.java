package com.example.oasis.dao;

import com.example.oasis.po.*;
import com.example.oasis.vo.AuthorVO;
import com.example.oasis.vo.PaperVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PortrayMapper {
    Keyword selectResearch(@Param("keyword") String keyword);
    List<AuthorVO> selectAuthorByField(String keyword);
    List<PaperVO> selectTitleByField(String keyword);

    //List<KeywordVO> selectTopResearch();

    List<TopField> selectPopField();

    List<PaperVO> selectTopPaperinField(@Param("keyword") String keyword);

    List<TopAuthor> selectTopAuthorInField(@Param("keyword") String keyword);

    List<TopAffiliation> selectTopAffiliationInField(@Param("keyword") String keyword);


}
