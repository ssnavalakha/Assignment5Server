package com.example.assignment5.repositories;

import com.example.assignment5.model.Topic;
import com.example.assignment5.model.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget,Long> {
    @Query("SELECT widget from Widget widget WHERE widget.topic.id=:tid ")
    public List<Widget> findWidgetByTid
            (@Param("tid") long tid);
}
