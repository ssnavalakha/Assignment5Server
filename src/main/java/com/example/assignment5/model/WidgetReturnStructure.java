package com.example.assignment5.model;

public class WidgetReturnStructure{
    long topicId;
    Widget widget;

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

    public WidgetReturnStructure(){}
    public WidgetReturnStructure(Widget w)
    {
        widget=w;
        topicId=w.getTopic().getId();
    }
}
