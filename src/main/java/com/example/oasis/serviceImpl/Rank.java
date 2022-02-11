package com.example.oasis.serviceImpl;

import com.example.oasis.dao.RankMapper;
import com.example.oasis.po.Affiliation;
import com.example.oasis.po.AuthorPic;
import com.example.oasis.po.PublicTrend;
import com.example.oasis.service.RankInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class Rank implements RankInter {
    @Autowired
    RankMapper rankMapper;

//    @Cacheable(value="rank1")
    public List<Affiliation> rankAffiliationByPapers(String publisher,int startYear,int endYear,String field,String conference){
        List<Affiliation> res=new ArrayList<>();
        if(conference.equals(""))
            return res;

        if(publisher.equals("IEEE")){
            if (field.equals("All")) {
                if (conference.equals("All"))
                    res = rankMapper.rankAllAffiliationByPapers(startYear, endYear);
                else {
                    String[] str = conference.split(";");
                    if (str.length == 1)
                        res = rankMapper.rankAffiliationByPapersConference(startYear, endYear, str[0]);
                    if (str.length == 2)
                        res = rankMapper.rankAffiliationByPapersConference2(startYear, endYear, str[0], str[1]);
                }
            } else {
                if (conference.equals("All"))
                    res = rankMapper.rankAffiliationByPapersField(startYear, endYear, field);
                else {
                    String[] str = conference.split(";");
                    if (str.length == 1)
                        res = rankMapper.rankAffiliationByPapersFieldConference(startYear, endYear, field, str[0]);
                    if (str.length == 2)
                        res = rankMapper.rankAffiliationByPapersFieldConference2(startYear, endYear, field, str[0], str[1]);
                }
            }
        }
//        else{
//            if(field.equals("All")){
//                if(conference.equals("All"))
//                    res=rankMapper.rankAffiliationByPapersCountry(country,startYear,endYear);
//                else
//                    res=rankMapper.rankAffiliationByPapersCountryConference(country,startYear,endYear,conference);
//            }
//            else{
//                if(conference.equals("All"))
//                    res=rankMapper.rankAffiliationByPapersCountryField(country,startYear,endYear,field);
//                else
//                    res=rankMapper.rankAffiliationByPapers(country,startYear,endYear,field,conference);
//            }
//       }

        return res;
    }

//    @Cacheable(value="rank2")
    public List<Affiliation> rankAffiliationByRefs(String publisher, int startYear, int endYear, String field, String conference) {
        List<Affiliation> res=new ArrayList<>();
        if(conference.equals(""))
            return res;

        if(publisher.equals("IEEE")){
            if(field.equals("All")){
                if(conference.equals("All"))
                    res=rankMapper.rankAllAffiliationByRefs(startYear,endYear);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAffiliationByRefsConference(startYear, endYear, str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAffiliationByRefsConference2(startYear, endYear, str[0],str[1]);
                }
            }
            else{
                if(conference.equals("All"))
                    res=rankMapper.rankAffiliationByRefsField(startYear,endYear,field);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAffiliationByRefsFieldConference(startYear, endYear, field,str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAffiliationByRefsFieldConference2(startYear, endYear, field,str[0],str[1]);
                }
            }
        }
//        else{
//            if(field.equals("All")){
//                if(conference.equals("All"))
//                    res=rankMapper.rankAffiliationByRefsCountry(country,startYear,endYear);
//                else
//                    res=rankMapper.rankAffiliationByRefsCountryConference(country,startYear,endYear,conference);
//            }
//            else{
//                if(conference.equals("All"))
//                    res=rankMapper.rankAffiliationByRefsCountryField(country,startYear,endYear,field);
//                else
//                    res=rankMapper.rankAffiliationByRefs(country,startYear,endYear,field,conference);
//            }
//        }
        return res;
    }
/*
    public List<Affiliation> rankAffiliationByAuthors(String country, int startYear, int endYear, String field, String conference) {
        if(country.equals("All")){
            if(field.equals("All")){
                if(conference.equals("All"))
                    return rankMapper.rankAllAffiliationByAuthors(startYear,endYear);
                else
                    return rankMapper.rankAffiliationByAuthorsConference(startYear,endYear,conference);
            }
            else{
                if(conference.equals("All"))
                    return rankMapper.rankAffiliationByAuthorsField(startYear,endYear,field);
                else
                    return rankMapper.rankAffiliationByAuthorsFieldConference(startYear,endYear,field,conference);
            }
        }
        else{
            if(field.equals("All")){
                if(conference.equals("All"))
                    return rankMapper.rankAffiliationByAuthorsCountry(country,startYear,endYear);
                else
                    return rankMapper.rankAffiliationByAuthorsCountryConference(country,startYear,endYear,conference);
            }
            else{
                if(conference.equals("All"))
                    return rankMapper.rankAffiliationByAuthorsCountryField(country,startYear,endYear,field);
                else
                    return rankMapper.rankAffiliationByAuthors(country,startYear,endYear,field,conference);
            }
        }
    }

 */

//    @Cacheable(value="rank3")
    public List<AuthorPic> rankAuthorByPapers(String publisher, int startYear, int endYear, String field, String conference) {
        List<AuthorPic> res=new ArrayList<>();
        if(conference.equals(""))
            return res;

        if(publisher.equals("IEEE")){
            if(field.equals("All")){
                if(conference.equals("All"))
                    return rankMapper.rankAllAuthorByPapers(startYear,endYear);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAuthorByPapersConference(startYear, endYear, str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAuthorByPapersConference2(startYear, endYear, str[0],str[1]);
                }
            }
            else{
                if(conference.equals("All"))
                    return rankMapper.rankAuthorByPapersField(startYear,endYear,field);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAuthorByPapersFieldConference(startYear, endYear,field, str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAuthorByPapersFieldConference2(startYear, endYear,field, str[0],str[1]);
                }
            }
        }

        /*else{
            if(field.equals("All")){
                if(conference.equals("All"))
                    res=rankMapper.rankAuthorByPapersCountry(publisher,startYear,endYear);
                else
                    res=rankMapper.rankAuthorByPapersCountryConference(country,startYear,endYear,conference);
            }
            else{
                if(conference.equals("All"))
                    res=rankMapper.rankAuthorByPapersCountryField(country,startYear,endYear,field);
                else
                    res=rankMapper.rankAuthorByPapers(country,startYear,endYear,field,conference);
            }
        }

         */

        return res;
    }

//    @Cacheable(value="rank4")
    public List<AuthorPic> rankAuthorByRefs(String publisher, int startYear, int endYear, String field, String conference) {
        List<AuthorPic> res=new ArrayList<>();
        if(conference.equals(""))
            return res;

        if(publisher.equals("IEEE")){
            if(field.equals("All")){
                if(conference.equals("All"))
                    res=rankMapper.rankAllAuthorByRefs(startYear,endYear);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAuthorByRefsConference(startYear, endYear, str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAuthorByRefsConference2(startYear, endYear, str[0],str[1]);
                }
            }
            else{
                if(conference.equals("All"))
                    res=rankMapper.rankAuthorByRefsField(startYear,endYear,field);
                else{
                    String[] str=conference.split(";");
                    if(str.length==1)
                        res = rankMapper.rankAuthorByRefsFieldConference(startYear, endYear,field, str[0]);
                    if(str.length==2)
                        res=rankMapper.rankAuthorByRefsFieldConference2(startYear, endYear,field, str[0],str[1]);
                }

            }
        }
        /*
        else{

            if(field.equals("All")){
                if(conference.equals("All"))
                    res=rankMapper.rankAuthorByRefsCountry(country,startYear,endYear);
                else
                    res=rankMapper.rankAuthorByRefsCountryConference(country,startYear,endYear,conference);
            }
            else{
                if(conference.equals("All"))
                    res=rankMapper.rankAuthorByRefsCountryField(country,startYear,endYear,field);
                else
                    res=rankMapper.rankAuthorByRefs(country,startYear,endYear,field,conference);
            }
        }

         */

        return res;
    }

    @Override
    public List<PublicTrend> getPublicTrend1(String name,int startYear, int endYear) {
        return rankMapper.getPublicTrend1(name,startYear,endYear);
    }

    @Override
    public List<PublicTrend> getPublicTrend2(String name, String affiliation, int startYear, int endYear) {
        return rankMapper.getPublicTrend2(name,affiliation,startYear,endYear);
    }
}
