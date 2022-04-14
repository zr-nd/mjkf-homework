package com.example.mjkf.vo;

public class PaperVO {
    private String title;

    private String authors;

    private String affiliations;

    private String publicTitle;

    private int year;

    private int startPage;

    private int endPage;

    private String pdfLink;

    private String authorKeyWord;

    private int referenceCount;

    private String publisher;

    private String docID;

    private String paperAbstract;


    public PaperVO(String title, String authors, String affiliations, String publicTitle, int year, int startPage, int endPage, String pdfLink, String authorKeyWord, int referenceCount, String publisher, String docID, String paperAbstract) {
        this.title = title;
        this.authors = authors;
        this.affiliations = affiliations;
        this.publicTitle = publicTitle;
        this.year = year;
        this.startPage = startPage;
        this.endPage = endPage;
        this.pdfLink = pdfLink;
        this.authorKeyWord = authorKeyWord;
        this.referenceCount = referenceCount;
        this.publisher = publisher;
        this.docID = docID;
        this.paperAbstract = paperAbstract;
    }

    public PaperVO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    public String getAuthorKeyWord() {
        return authorKeyWord;
    }

    public void setAuthorKeyWord(String authorKeyWord) {
        this.authorKeyWord = authorKeyWord;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }
}
