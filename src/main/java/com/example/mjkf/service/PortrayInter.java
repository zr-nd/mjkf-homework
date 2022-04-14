package com.example.mjkf.service;

import com.example.mjkf.po.*;
import com.example.mjkf.vo.PaperVO;

import java.util.List;

public interface PortrayInter {
    Keyword getKeywordPortray(String keyword);
    List<PaperVO> selectTopPaperinField(String keyword);
    List<TopField> selectPopField();
    List<TopAuthor> selectTopAuthorInField(String keyword);
    List<TopAffiliation> selectTopAffiliationInField(String keyword);

}
