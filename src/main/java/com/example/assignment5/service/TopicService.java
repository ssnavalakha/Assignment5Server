package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.Lesson;
import com.example.assignment5.model.Module;
import com.example.assignment5.model.Topic;
import com.example.assignment5.repositories.LessonRepository;
import com.example.assignment5.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TopicService {
    @Autowired
    TopicRepository repo;
    @Autowired
    LessonRepository lrepo;

    @PostMapping(path = "/api/lesson/{lid}/topic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic createTopic(@PathVariable("lid")long lid,
                             @RequestBody Topic t) {
        Topic newTopic=new Topic(t.getId(),lrepo.findById(lid).get(),t.getWidgets(),t.getTitle());
        repo.save(newTopic);
        return newTopic;
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid")long lid,
                                     HttpSession session) {
        return repo.findTopicByLid(lid);
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(
            @PathVariable("tid") long id) {
        Optional<Topic> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/topic/{tid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic updateTopic (@PathVariable("tid")long id,
                              @RequestBody Lesson l) {
        Optional<Topic> x= repo.findById(id);
        final Topic[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                Topic temp2=l.getTopics().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                y.setLesson(temp2.getLesson());
                y.setTitle(temp2.getTitle());
                y.setWidgets(temp2.getWidgets());
                repo.save(y);
                });
        return temp[0];
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(
            @PathVariable("tid") long id) {
        repo.deleteById(id);
    }
}
