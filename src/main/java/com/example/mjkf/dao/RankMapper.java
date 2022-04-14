package com.example.mjkf.dao;

import com.example.mjkf.po.Affiliation;
import com.example.mjkf.po.AuthorPic;
import com.example.mjkf.po.PublicTrend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RankMapper {
    //List<Affiliation> rankAffiliationByPapers(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    //List<Affiliation> rankAffiliationByRefs(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    //List<Affiliation> rankAffiliationByAuthors(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAllAffiliationByPapers(@Param("startYear")int startYear,@Param("endYear")int endYear);

    //List<Affiliation> rankAffiliationByPapersCountry(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAffiliationByPapersField(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);
    List<Affiliation> rankAffiliationByPapersConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByPapersConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference1")String conference1,@Param("conference2")String conference2);
    List<Affiliation> rankAffiliationByPapersFieldConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByPapersFieldConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference1")String conference1,@Param("conference2")String conference2);
    List<Affiliation> rankAffiliationByPapersCountryConference(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByPapersCountryField(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);

    List<Affiliation> rankAllAffiliationByRefs(@Param("startYear")int startYear,@Param("endYear")int endYear);
    //List<Affiliation> rankAffiliationByRefsCountry(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAffiliationByRefsField(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);
    List<Affiliation> rankAffiliationByRefsConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByRefsConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference1")String conference1,@Param("conference2")String conference2);
    List<Affiliation> rankAffiliationByRefsFieldConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByRefsFieldConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference1")String conference1,@Param("conference2")String conference2);
    //List<Affiliation> rankAffiliationByRefsCountryConference(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    //List<Affiliation> rankAffiliationByRefsCountryField(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);

    /*List<Affiliation> rankAllAffiliationByAuthors(@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAffiliationByAuthorsCountry(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAffiliationByAuthorsField(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);
    List<Affiliation> rankAffiliationByAuthorsConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByAuthorsFieldConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByAuthorsCountryConference(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAffiliationByAuthorsCountryField(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);


     */
    //List<AuthorPic> rankAuthorByPapers(@Param("country")String country, @Param("startYear")int startYear, @Param("endYear")int endYear, @Param("field")String field, @Param("conference")String conference);
    //List<AuthorPic> rankAuthorByRefs(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAllAuthorByPapers(@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAllAuthorByRefs(@Param("startYear")int startYear,@Param("endYear")int endYear);

    //List<AuthorPic> rankAuthorByPapersCountry(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAuthorByPapersField(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);
    List<Affiliation> rankAuthorByPapersConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAuthorByPapersConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference1")String conference1,@Param("conference2")String conference2);
    List<Affiliation> rankAuthorByPapersFieldConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAuthorByPapersFieldConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference1")String conference1,@Param("conference2")String conference2);
    //List<AuthorPic> rankAuthorByPapersCountryConference(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    //List<AuthorPic> rankAuthorByPapersCountryField(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);

    //List<AuthorPic> rankAuthorByRefsCountry(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<Affiliation> rankAuthorByRefsField(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);
    List<Affiliation> rankAuthorByRefsConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    List<Affiliation> rankAuthorByRefsConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference1")String conference1,@Param("conference2")String conference2);
    List<Affiliation> rankAuthorByRefsFieldConference(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference")String conference);
    List<Affiliation> rankAuthorByRefsFieldConference2(@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field,@Param("conference1")String conference1,@Param("conference2")String conference2);
    //List<AuthorPic> rankAuthorByRefsCountryConference(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("conference")String conference);
    //List<AuthorPic> rankAuthorByRefsCountryField(@Param("country")String country,@Param("startYear")int startYear,@Param("endYear")int endYear,@Param("field")String field);


    List<PublicTrend> getPublicTrend1(@Param("name") String name,@Param("startYear")int startYear,@Param("endYear")int endYear);
    List<PublicTrend> getPublicTrend2(@Param("name") String name,@Param("affiliation") String affiliation,@Param("startYear")int startYear,@Param("endYear")int endYear);
}
