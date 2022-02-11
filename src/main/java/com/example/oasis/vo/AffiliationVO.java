package com.example.oasis.vo;

import com.example.oasis.po.AuthorAffiliation;

import java.util.List;

public class AffiliationVO {
    private String name;
    private int authors;
    private int papers;
    private int citations;
    private List<AuthorAffiliation> authorName;

    public AffiliationVO(String name, int authors,int papers,int citations,List<AuthorAffiliation> authorName) {
        this.name = name;
        this.authors=authors;
        this.papers=papers;
        this.citations=citations;
        this.authorName=authorName;
    }

    public String getName() {
        return name;
    }

    public int getAuthors() {
        return authors;
    }

    public int getPapers() {
        return papers;
    }

    public int getCitations() {
        return citations;
    }

    public List<AuthorAffiliation> getAuthorName() {
        return authorName;
    }



}
