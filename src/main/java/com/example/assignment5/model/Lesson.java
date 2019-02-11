package com.example.assignment5.model;

import java.util.List;

public class Lesson {
    private long moduleId;
    private long id;
    private List<Topic> topics;
    private String title;

    public Lesson(){}

    public Lesson(long moduleId, long id, List<Topic> topics, String title) {
        this.moduleId = moduleId;
        this.id = id;
        this.topics = topics;
        this.title = title;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
