package com.example.mjkf.dao;



import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.ConferPic;
import com.example.mjkf.po.Paper;
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
