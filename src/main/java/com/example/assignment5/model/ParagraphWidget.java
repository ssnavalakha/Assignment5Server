package com.example.assignment5.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PARAGRAPH")
public class ParagraphWidget extends Widget {
    private String text;

    public ParagraphWidget(long id, Topic topic, long position, long up, long down, String type, String text) {
        super(id, topic, position, up, down, type,0);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ParagraphWidget(String text) {
        this.text = text;
    }

    public ParagraphWidget()
    {

    }
}
