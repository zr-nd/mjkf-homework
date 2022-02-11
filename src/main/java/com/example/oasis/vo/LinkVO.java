package com.example.oasis.vo;

import com.example.oasis.po.Links;
import com.example.oasis.po.Nodes;

import java.util.List;

public class LinkVO {
    private List<Nodes> nodesList;
    private List<Links> linksList;

    public LinkVO(List<Nodes> nodesList,List<Links> linksList) {
        this.nodesList=nodesList;
        this.linksList=linksList;
    }

    public List<Nodes> getNodesList() {
        return nodesList;
    }

    public List<Links> getLinksList() {
        return linksList;
    }


}
