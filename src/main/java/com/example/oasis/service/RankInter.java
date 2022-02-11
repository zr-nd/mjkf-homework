package com.example.oasis.service;

import com.example.oasis.po.Affiliation;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.PublicTrend;

import java.util.List;

public interface RankInter {
    List<Affiliation> rankAffiliationByPapers(String publisher,int startYear,int endYear,String field,String conference);
    List<Affiliation> rankAffiliationByRefs(String publisher,int startYear,int endYear,String field,String conference);
    //List<Affiliation> rankAffiliationByAuthors(String country,int startYear,int endYear,String field,String conference);

    List<AuthorPic> rankAuthorByPapers(String publisher, int startYear, int endYear, String field, String conference);
    List<AuthorPic> rankAuthorByRefs(String publisher, int startYear, int endYear, String field, String conference);

    List<PublicTrend> getPublicTrend1(String name, int startYear, int endYear);
    List<PublicTrend> getPublicTrend2(String name,String affiliation, int startYear, int endYear);
}
