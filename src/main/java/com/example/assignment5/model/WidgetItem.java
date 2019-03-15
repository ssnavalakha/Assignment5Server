package com.example.assignment5.model;

public class WidgetItem {
    private long id;
    private long position;
    private long up;
    private long down;
    private String type;
    private long size;
    private String text;
    private String src;
    private String href;
    private String title;
    private String items;
    private long ddType;
    private long topicId;

    public long getTopicId() {
        return topicId;
    }

    public WidgetItem(long id, long position, long up,
                      long down, String type, long size, String text, String src,
                      String href, String title, String items, long ddType, long topicId) {
        this.id = id;
        this.position = position;
        this.up = up;
        this.down = down;
        this.type = type;
        this.size = size;
        this.text = text;
        this.src = src;
        this.href = href;
        this.title = title;
        this.items = items;
        this.ddType = ddType;
        this.topicId = topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public WidgetItem(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public long getDdType() {
        return ddType;
    }

    public void setDdType(long ddType) {
        this.ddType = ddType;
    }
}
