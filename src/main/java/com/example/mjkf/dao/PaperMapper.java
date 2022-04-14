package com.example.mjkf.dao;



import com.example.mjkf.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PaperMapper {  //和低层数据库的接口
    //论文查找
    List<Paper> selectPaper(@Param("searchField") String searchField);
    List<Paper> selectPaperByTwo(@Param("searchField1") String searchField1,@Param("searchField2") String searchField2);
    List<Paper> selectPaperByThree(@Param("searchField1") String searchField1,@Param("searchField2") String searchField2,@Param("searchField3") String searchField3);
    List<Paper> selectPaperByFour(@Param("searchField1") String searchField1,@Param("searchField2") String searchField2,@Param("searchField3") String searchField3,@Param("searchField4") String searchField4);
    //加年份区间的搜索
    List<Paper> selectPaperYear(@Param("year1") int year1,@Param("year2") int year2);
    List<Paper> selectPaperByTwoYear(@Param("searchField1") String searchField1,@Param("year1") int year1,@Param("year2") int year2);
    List<Paper> selectPaperByThreeYear(@Param("searchField1") String searchField1,@Param("searchField2") String searchField2,@Param("year1") int year1,@Param("year2") int year2);
    List<Paper> selectPaperByFourYear(@Param("searchField1") String searchField1,@Param("searchField2") String searchField2,@Param("searchField3") String searchField3,@Param("year1") int year1,@Param("year2") int year2);
    Affiliation selectAffiliation(@Param("name") String name);
    List<AuthorAffiliation> selectAuthorList(@Param("name") String name);
    List<TopAffiliation> selectTopAffiliation();
    List<AffiliationField> getAffiliationField(@Param("name") String name);

    List<Nodes> getNodes();
    List<Links> getLinks();

    List<Nodes> getAffiliationNodes();
    List<Links> getAffiliationLinks();
}
