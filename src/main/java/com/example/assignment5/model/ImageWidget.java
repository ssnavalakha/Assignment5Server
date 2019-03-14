package com.example.assignment5.model;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget {
    private String src;

    public ImageWidget(String src) {
        this.src = src;
    }

    public ImageWidget(){}

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
