package com.example.mjkf.po;

public class Author {
    private String name;
    private int count;

    public Author(){}

    public Author(String name,int count){
        this.name=name;
        this.count=count;
    }

    public void setName(String name){this.name=name;}
    public void setCount(int count){this.count=count;}
    public String getName(){return name;}
    public int getCount(){return count;}
}
