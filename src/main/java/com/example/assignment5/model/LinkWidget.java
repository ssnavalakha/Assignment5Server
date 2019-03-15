package com.example.assignment5.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "LINK")
public class LinkWidget extends Widget {
    private String href;
    private String title;

    public LinkWidget(long id, Topic topic, long position, long up, long down, String type, String href, String title) {
        super(id, topic, position, up, down, type,0);
        this.href = href;
        this.title = title;
    }

    public LinkWidget(String href, String title) {
        this.href = href;
        this.title = title;
    }

    public LinkWidget(){}

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
