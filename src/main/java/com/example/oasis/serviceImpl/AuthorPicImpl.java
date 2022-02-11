package com.example.oasis.serviceImpl;

import com.example.oasis.dao.AuthorPicMapper;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.Paper;
import com.example.oasis.service.AuthorPicInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorPicImpl implements AuthorPicInter {

    @Autowired
    private AuthorPicMapper authorPicMapper;

    public List<AuthorPic> getMostAuthorPic(){
        return authorPicMapper.getMostAuthorPic();
    }//获得作者画像

    public AuthorPic getAuthorPicByName(String author, String affiliation){
        return authorPicMapper.getAuthorPicByName(author,affiliation);
    }

    public AuthorPic getAuthorPicByNameAndTitle(String author, String title){
        return authorPicMapper.getAuthorPicByNameAndTitle(author,title);
    }

    public List<Paper> getPapersByAuthorPic(String author, String affiliation) {
        return authorPicMapper.getPapersByAuthorPic(author,affiliation);
    }//根据作者来获得他的文章
}
