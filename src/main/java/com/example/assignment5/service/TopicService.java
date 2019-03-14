package com.example.assignment5.service;

import com.example.assignment5.model.*;
import com.example.assignment5.repositories.LessonRepository;
import com.example.assignment5.repositories.TopicRepository;
import com.example.assignment5.repositories.WidgetRepository;
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
    @Autowired
    WidgetRepository wrepo;

    @PostMapping(path = "/api/lesson/{lid}/topic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic createTopic(@PathVariable("lid")long lid,
                             @RequestBody Topic t) {
        Topic newTopic=new Topic(t.getId(),lrepo.findById(lid).get(),t.getWidgets(),t.getTitle());
        repo.save(newTopic);
        return newTopic;
    }

    @PostMapping(path = "/api/topic/{tid}/widget", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Widget createWidget(@PathVariable("tid")long tid
                               ,@RequestBody WidgetItem w) {
        Widget newWidget=w.getWidget();
        wrepo.save(newWidget);
        return newWidget;
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid")long lid,
                                     HttpSession session) {
        return repo.findTopicByLid(lid);
    }

    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgetsForTopic(@PathVariable("tid")long tid,
                                     HttpSession session) {
        return wrepo.findWidgetByTid(tid);
    }

    @GetMapping("/api/topic/widgets")
    public List<Widget> findAllWidgets(HttpSession session) {
        return (List<Widget>) wrepo.findAll();
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
