package com.example.assignment5.model;

import java.util.List;

public class Topic {
    private long id;
    private long lessonId;
    private List<WidgetItem> widgets;
    private String title;

    public Topic()
    {

    }

    public Topic(long id, long lessonId, List<WidgetItem> widgets, String title) {
        this.id = id;
        this.lessonId = lessonId;
        this.widgets = widgets;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public List<WidgetItem> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetItem> widgets) {
        this.widgets = widgets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
