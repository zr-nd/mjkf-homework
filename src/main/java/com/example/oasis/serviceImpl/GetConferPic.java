package com.example.oasis.serviceImpl;

import com.example.oasis.dao.ConferPicMapper;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.ConferPic;
import com.example.oasis.po.Paper;
import com.example.oasis.service.ConferPicInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConferPic implements ConferPicInter {

    @Autowired
    private ConferPicMapper conferPicMapper;

    public List<ConferPic> getConferPic(){
        return conferPicMapper.selectConferPic();
    }

    public List<Paper> getPaperByConferenceName(String name){
        return conferPicMapper.selectPaperByConferName(name);
    }

    public List<AuthorPic> getAuthorByConferenceName(String name){
        return conferPicMapper.selectAuthorByConferName(name);
    }
}
