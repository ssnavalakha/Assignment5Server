package com.example.assignment5.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Widget {

    @Id
    private long id;
    @ManyToOne()
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    private Topic topic;
    private long size;
    private String text;
    String items;
    private String src;
    private String href;
    private String title;
    private long ddType;
    private long position;
    private long up;
    private long down;
    private String type;

    public Widget(){}
    public Widget(long id, Topic topic, long size,
                  String text, String items, String src, String href,
                  String title, long ddType, long position,
                  long up, long down, String type) {
        this.id = id;
        this.topic = topic;
        this.size = size;
        this.text = text;
        this.items = items;
        this.src = src;
        this.href = href;
        this.title = title;
        this.ddType = ddType;
        this.position = position;
        this.up = up;
        this.down = down;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
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

    public long getDdType() {
        return ddType;
    }

    public void setDdType(long ddType) {
        this.ddType = ddType;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
