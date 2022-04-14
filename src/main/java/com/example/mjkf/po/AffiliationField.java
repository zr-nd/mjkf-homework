package com.example.mjkf.po;

public class AffiliationField {
    private String name;
    private String authorKeyWord;
    private double active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorKeyWord() {
        return authorKeyWord;
    }

    public double getActive() {
        return active;
    }

    public void setAuthorKeyWord(String authorKeyWord) {
        this.authorKeyWord = authorKeyWord;
    }

    public void setActive(double active) {
        this.active = active;
    }
}
