package com.example.assignment5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="topic")
public class Topic {
    @Id
    private long id;
    @OneToMany(mappedBy = "topic")
    private List<Widget> widgets;
    private String title;
    @ManyToOne()
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;


    public Topic()
    {

    }

    public Topic(long id, Lesson lesson, List<Widget> widgets, String title) {
        this.id = id;
        this.lesson = lesson;
        this.widgets = widgets;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
