package com.example.oasis.vo;

public class AuthorVO {
    private String name;

    public AuthorVO(String name){
        this.name=name;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}
}
