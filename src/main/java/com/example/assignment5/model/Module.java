package com.example.assignment5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="module")
public class Module {
    @Id
    private long id;
    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;
    private String title;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    public Module(){}

    public Module(long id, Course course, List<Lesson> lessons, String title) {
        this.id = id;
        this.course = course;
        this.lessons = lessons;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
