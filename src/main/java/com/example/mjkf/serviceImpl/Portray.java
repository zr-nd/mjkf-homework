package com.example.mjkf.serviceImpl;

import com.example.mjkf.dao.PaperMapper;
import com.example.mjkf.dao.PortrayMapper;
import com.example.mjkf.po.*;
import com.example.mjkf.service.PortrayInter;
import com.example.mjkf.vo.PaperVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Portray implements PortrayInter {
    @Autowired
    PortrayMapper portrayMapper;
    @Autowired
    PaperMapper paperMapper;

    public Keyword getKeywordPortray(String keyword){
        Keyword k=portrayMapper.selectResearch(keyword);
        k.setAuthors(portrayMapper.selectAuthorByField(keyword));
        k.setTitle(portrayMapper.selectTitleByField(keyword));
        return k;
    }

    public List<PaperVO> selectTopPaperinField(String keyword){
        List<PaperVO> searchRes=portrayMapper.selectTopPaperinField(keyword);

        return searchRes;
    }

    public List<TopField> selectPopField(){
        List<TopField> res=portrayMapper.selectPopField();

        return res;
    }

    public List<TopAuthor> selectTopAuthorInField(String keyword){
        List<TopAuthor> tmp=portrayMapper.selectTopAuthorInField(keyword);
        /*List<TopAuthor> res=new ArrayList<>();
        if(tmp.size()<=10){
            for(int i=0;i<tmp.size();i++)
                res.add(tmp.get(i));
        }
        else{
            for(int i=0;i<10;i++)
                res.add(tmp.get(i));
            double other=0.0;
            for(int i=10;i<tmp.size();i++)
                other+=tmp.get(i).getActive();
            TopAuthor t=new TopAuthor();
            t.setActive(other);
            t.setAuthor("others");
            res.add(t);
        }

         */
        return tmp;
    }

    public List<TopAffiliation> selectTopAffiliationInField(String keyword){
        List<TopAffiliation> res=portrayMapper.selectTopAffiliationInField(keyword);
        return res;
    }


}
