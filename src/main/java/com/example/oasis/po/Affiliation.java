package com.example.oasis.po;

import java.io.Serializable;
import java.util.List;

public class Affiliation{
    private String name;
    private int authors;
    private int papers;
    private int refs;

    public Affiliation(){}

    public Affiliation(String name, int authors, int papers, int refs) {
        this.name = name;
        this.authors=authors;
        this.papers=papers;
        this.refs=refs;

    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name=name;}

    public int getAuthors() {
        return authors;
    }

    public int getPapers() {
        return papers;
    }

    public int getRefs() {
        return refs;
    }

    public void setPapers(int papers) {
        this.papers = papers;
    }

    public void setRefs(int refs) {
        this.refs = refs;
    }
}
