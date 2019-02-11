package com.example.assignment5.model;

import java.util.List;

public class Module {
    private long id;
    private long courseId;
    private List<Lesson> lessons;
    private String title;

    public Module(){}

    public Module(long id, long courseId, List<Lesson> lessons, String title) {
        this.id = id;
        this.courseId = courseId;
        this.lessons = lessons;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
