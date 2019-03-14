package com.example.assignment5.model;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ParagraphWidget(String text) {
        this.text = text;
    }
}
