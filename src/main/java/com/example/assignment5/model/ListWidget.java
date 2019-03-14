package com.example.assignment5.model;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget {
    String items;
    private long ddType;

    public ListWidget(String items, long ddType) {
        this.items = items;
        this.ddType = ddType;
    }

    public ListWidget(){}

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
