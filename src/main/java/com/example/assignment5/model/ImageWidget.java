package com.example.assignment5.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "IMAGE")
public class ImageWidget extends Widget {
    private String src;

    public ImageWidget(long id, Topic topic, long position, long up, long down, String type, String src) {
        super(id, topic, position, up, down, type,0);
        this.src = src;
    }

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
