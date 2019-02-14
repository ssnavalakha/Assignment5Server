package com.example.assignment5.model;

public class WidgetItem {
    private long topicId;
    private Widget widget;

    public WidgetItem(long topicId, Widget widget) {
        this.topicId = topicId;
        this.widget = widget;
    }
    public WidgetItem(){}

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
