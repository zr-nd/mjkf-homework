package com.example.oasis.po;

public class Nodes {
    private int category;
    private String name;
    private double value;
    private double symbolSize;
    private String label;

    public Nodes(int category,String name,double value,double symbolSize,String label){
        this.category=category;
        this.name=name;
        this.value=value;
        this.symbolSize= symbolSize;
        this.label = label;

    }

    public Nodes(String name,double value,double symbolSize,String label){
        this.category=0;
        this.name=name;
        this.value=value;
        this.symbolSize= symbolSize;
        this.label = label;

    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(double symbolSize) {
        this.symbolSize = symbolSize;
    }

    public String getLabel() {
        return label;
    }



}
