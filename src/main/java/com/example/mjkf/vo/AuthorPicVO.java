package com.example.mjkf.vo;

public class AuthorPicVO {
    private String author;
    private String affiliation;
    private int papers;
    private int refs;

    public AuthorPicVO(){}
    public AuthorPicVO(String author, String affiliation, int papers, int refs){
        this.author = author;
        this.affiliation=affiliation;
        this.papers=papers;
        this.refs=refs;
    }
    public void setAuthor(String author){this.author=author;}
    public void setAffiliation(String affiliation){this.affiliation = affiliation;}
    public void setPapers(int papers){this.papers = papers;}
    public void setRefs(int refs){this.refs = refs;}
    public String getAuthor(){return author;}
    public String getAffiliation(){return affiliation;}
    public int getPapers(){return papers;}
    public int getRefs(){return refs;}
}
