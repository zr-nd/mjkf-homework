package com.example.oasis.po;

public class Links {
    private String source;
    private String target;
    private int weight;
    public Links(String source,String target){
        this.source=source;
        this.target=target;
        this.weight=1;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getWeight() { return weight; }
}
