package com.example.oasis.service;

import com.example.oasis.po.*;
import com.example.oasis.vo.PaperVO;

import java.util.List;

public interface PortrayInter {
    Keyword getKeywordPortray(String keyword);
    List<PaperVO> selectTopPaperinField(String keyword);
    List<TopField> selectPopField();
    List<TopAuthor> selectTopAuthorInField(String keyword);
    List<TopAffiliation> selectTopAffiliationInField(String keyword);

}
