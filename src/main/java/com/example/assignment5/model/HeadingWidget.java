package com.example.assignment5.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "HEADING")
public class HeadingWidget extends Widget {
    private long size;
    private String text;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HeadingWidget(long size, String text) {
        this.size = size;
        this.text = text;
    }

    public HeadingWidget(long id, Topic topic, long position, long up, long down, String type,
                         long size, String text) {
        super(id, topic, position, up, down, type,0);
        this.size = size;
        this.text = text;
    }

    public HeadingWidget(){
    }
}
