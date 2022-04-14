package com.example.mjkf.service;


import com.example.mjkf.po.AffiliationField;
import com.example.mjkf.po.Paper;
import com.example.mjkf.po.TopAffiliation;
import com.example.mjkf.vo.AffiliationVO;
import com.example.mjkf.vo.LinkVO;

import java.util.List;

public interface SearchInter {
    List<Paper> getListPaper(String searchField);
    List<Paper> getListPaperByCombina(String searchField);
    AffiliationVO getAffiliation(String name);
    List<TopAffiliation> getTopAffiliation();
    List<AffiliationField> getAffiliationField(String name);

    LinkVO getNodesLinks();


    //获取机构的节点和节点之间的联系
    LinkVO getAffiliationNodesAndLinks();
}
