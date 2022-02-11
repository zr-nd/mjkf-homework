package com.example.oasis.serviceImpl;


import com.example.oasis.po.*;
import com.example.oasis.service.SearchInter;
import com.example.oasis.vo.AffiliationVO;
import com.example.oasis.vo.LinkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.example.oasis.po.Paper;
import com.example.oasis.dao.PaperMapper;

@Service
public class Search implements SearchInter {
    /*

    注入dao
     */
    @Autowired
    private PaperMapper paperMapper;


    //单值查询
    public List<Paper> getListPaper(String searchField){
        if(Pattern.matches("(.*)(\\d{4,4})(-)(\\d{4,4})(.*)",searchField)){
            String[] searchList=searchField.split("-");
            int year1=Integer.valueOf(searchList[0]);
            int year2=Integer.valueOf(searchList[1]);
            return(paperMapper.selectPaperYear(year1,year2));
        }
        return(paperMapper.selectPaper(searchField));
    }

    //组合查询
    public List<Paper> getListPaperByCombina(String searchField){
        int count=searchField.length()-searchField.replace("&","").length();
        if(Pattern.matches("(.*)(\\d{4,4})(-)(\\d{4,4})(.*)",searchField)){
            ArrayList<String> searchList=new ArrayList<>();
            String[] searchList1=searchField.split("-");
            String[] searchList2=searchList1[0].split("&");
            String[] searchList3=searchList1[1].split("&");
            int year1=Integer.valueOf(searchList2[searchList2.length-1]);
            int year2=Integer.valueOf(searchList3[0]);
            for(int i=0;i<searchList2.length-1;i++){
                searchList.add(searchList2[i]);
            }
            for(int i=1;i<searchList3.length;i++){
                searchList.add(searchList3[i]);
            }
            if(searchList.size()==1){
                return(paperMapper.selectPaperByTwoYear(searchList.get(0),year1,year2));
            }
            else if(searchList.size()==2){
                return(paperMapper.selectPaperByThreeYear(searchList.get(0),searchList.get(1),year1,year2));
            }
            else if(searchList.size()==3){
                return(paperMapper.selectPaperByFourYear(searchList.get(0),searchList.get(1),searchList.get(2),year1,year2));
            }
        }
        else {
            String[] searchList = searchField.split("&");
            if (count == 1) {
                return (paperMapper.selectPaperByTwo(searchList[0], searchList[1]));
            } else if (count == 2) {
                return (paperMapper.selectPaperByThree(searchList[0], searchList[1], searchList[2]));
            } else {
                return (paperMapper.selectPaperByFour(searchList[0], searchList[1], searchList[2], searchList[3]));
            }
        }
        return null;
    }

    public AffiliationVO getAffiliation(String name){
        Affiliation aff=paperMapper.selectAffiliation(name);
        List<AuthorAffiliation> aut_aff=paperMapper.selectAuthorList(name);
        return new AffiliationVO(aff.getName(),aff.getAuthors(),aff.getPapers(),aff.getRefs(),aut_aff);
    }

    public List<TopAffiliation> getTopAffiliation(){
        return(paperMapper.selectTopAffiliation());
    }

    public List<AffiliationField> getAffiliationField(String name){
        List<AffiliationField> res=paperMapper.getAffiliationField(name);
        return res;
    }

    public LinkVO getNodesLinks(){
        LinkVO linkVO=new LinkVO(paperMapper.getNodes(),paperMapper.getLinks());
        return linkVO;
    }

    public LinkVO getAffiliationNodesAndLinks(){
        LinkVO linkVO = new LinkVO(paperMapper.getAffiliationNodes(),paperMapper.getAffiliationLinks());
        return linkVO;
    }
}
