package com.example.assignment5.repositories;

import com.example.assignment5.model.Lesson;
import com.example.assignment5.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic,Long> {
    @Query("SELECT topic from Topic topic WHERE topic.lesson.id=:lid ")
    public List<Topic> findTopicByLid
            (@Param("lid") long lid);
}
