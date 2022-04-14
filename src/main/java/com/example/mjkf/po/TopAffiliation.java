package com.example.mjkf.po;

public class TopAffiliation {
    private String name;

    private double active;

    public TopAffiliation(String name,double active) {
        this.name = name;
        this.active=active;
    }

    public TopAffiliation(){}

    public String getName() {
        return name;
    }
    public void setName(String name){this.name=name;}


    public double getActive() {
        return active;
    }

    public void setActive(double active) {
        this.active = active;
    }
}
