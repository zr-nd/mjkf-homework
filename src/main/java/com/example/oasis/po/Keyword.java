package com.example.oasis.po;

import com.example.oasis.vo.AuthorVO;
import com.example.oasis.vo.PaperVO;

import java.util.ArrayList;
import java.util.List;

public class Keyword {
    private String keyword;
    private int author_count;
    private int paper_count;
    private int reference_count;
    private List<String> authors;
    private List<String> title;

    public Keyword(){}

    public void setKeyword(String keyword){this.keyword=keyword;}
    public String getKeyword(){return keyword;}
    public void setAuthor_count(int n){this.author_count=n;}
    public int getAuthor_count(){return author_count;}
    public void setPaper_count(int n){this.paper_count=n;}
    public int getPaper_count(){return paper_count;}
    public void setReference_count(int n){this.reference_count=n;}
    public int getReference_count(){return reference_count;}

    public void setAuthors(List<AuthorVO> input){
        List<String> t=new ArrayList<>();
        for(int i=0;i<input.size();i++)
            t.add(input.get(i).getName());
        this.authors=t;
    }
    public List<String> getAuthors(){return authors;}
    public List<String> getTitle(){return title;}

    public void setTitle(List<PaperVO> input){
        List<String> t=new ArrayList<>();
        for(int i=0;i<input.size();i++)
            t.add(input.get(i).getTitle());
        this.title=t;
    }
}
