package com.example.assignment5.model;

import javax.persistence.Entity;

@Entity
public class LinkWidget extends Widget {
    private String href;
    private String title;

    public LinkWidget(String href, String title) {
        this.href = href;
        this.title = title;
    }

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
