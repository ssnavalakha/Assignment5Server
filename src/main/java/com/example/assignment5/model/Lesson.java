package com.example.assignment5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="lesson")
public class Lesson {
    @Id
    private long id;
    @OneToMany(mappedBy = "lesson")
    private List<Topic> topics;
    private String title;

    @ManyToOne()
    @JoinColumn(name = "module_id")
    private Module module;

    public Lesson(){}

    public Lesson(Module moduleId, long id, List<Topic> topics, String title) {
        this.module = moduleId;
        this.id = id;
        this.topics = topics;
        this.title = title;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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
