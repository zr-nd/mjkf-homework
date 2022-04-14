package com.example.mjkf.po;

public class TopField {
    private String authorKeyWord;
    private double active;

    public TopField(){}

    public TopField(String s){
        this.authorKeyWord=s;
    }

    public void setAuthorKeyWord(String authorKeyWord) {
        this.authorKeyWord = authorKeyWord;
    }

    public void setActive(double active) {
        this.active = active;
    }

    public String getAuthorKeyWord() {
        return authorKeyWord;
    }

    public double getActive() {
        return active;
    }
}
