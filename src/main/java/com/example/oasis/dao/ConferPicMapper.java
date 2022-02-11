package com.example.oasis.dao;



import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.ConferPic;
import com.example.oasis.po.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ConferPicMapper {

    List<ConferPic> selectConferPic();

    List<Paper> selectPaperByConferName(String name);

    List<AuthorPic> selectAuthorByConferName(String name);

}
