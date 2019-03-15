package com.example.assignment5.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Widget {

    @Id
    private long id;
    @ManyToOne()
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    private Topic topic;
    private long position;
    private long up;
    private long down;
    private String type;

    private long ddType;

    public Widget(){}
    public Widget(long id, Topic topic, long position,
                  long up, long down, String type,long ddType) {
        this.id = id;
        this.topic = topic;
        this.position = position;
        this.up = up;
        this.down = down;
        this.type = type;
        this.ddType=ddType;
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

    public void setDdType(long ddType){this.ddType=ddType;}

    public long getDdType(){return this.ddType;}
}
