package com.example.mjkf.po;

import java.math.BigDecimal;

public class AuthorAffiliation {
    private String author;

    private String affiliation;

    private long papers;

    private BigDecimal citations;

    public AuthorAffiliation(String author, String affiliations, long papers, BigDecimal citations) {
        this.author = author;
        this.affiliation=affiliations;
        this.papers=papers;
        this.citations=citations;
    }

    public String getAuthor() {
        return author;
    }



    public String getAffiliation() {
        return affiliation;
    }


    public long getPapers() {
        return papers;
    }


    public BigDecimal getCitations() {
        return citations;
    }




}
