package com.example.oasis.service;


import com.example.oasis.po.AffiliationField;
import com.example.oasis.po.Paper;
import com.example.oasis.po.TopAffiliation;
import com.example.oasis.vo.AffiliationVO;
import com.example.oasis.vo.LinkVO;

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
