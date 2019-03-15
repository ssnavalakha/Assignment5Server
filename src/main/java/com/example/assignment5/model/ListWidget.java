package com.example.assignment5.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "LIST")
public class ListWidget extends Widget {
    String items;

    public ListWidget(long id, Topic topic, long position, long up, long down, String type, String items,long ddType) {
        super(id, topic, position, up, down, type,ddType);
        this.items = items;

    }

    public ListWidget(String items) {
        this.items = items;
    }

    public ListWidget(){}

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

}
