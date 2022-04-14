package com.example.mjkf.service;

import com.example.mjkf.po.Affiliation;
import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.PublicTrend;

import java.util.List;

public interface RankInter {
    List<Affiliation> rankAffiliationByPapers(String publisher,int startYear,int endYear,String field,String conference);
    List<Affiliation> rankAffiliationByRefs(String publisher,int startYear,int endYear,String field,String conference);
    //List<Affiliation> rankAffiliationByAuthors(String country,int startYear,int endYear,String field,String conference);

    List<Affiliation> rankAuthorByPapers(String publisher, int startYear, int endYear, String field, String conference);
    List<Affiliation> rankAuthorByRefs(String publisher, int startYear, int endYear, String field, String conference);

    List<PublicTrend> getPublicTrend1(String name, int startYear, int endYear);
    List<PublicTrend> getPublicTrend2(String name,String affiliation, int startYear, int endYear);
}
