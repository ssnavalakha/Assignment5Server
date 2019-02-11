package com.example.assignment5.service;

import com.example.assignment5.model.Topic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TopicService {
    static ArrayList<Topic> topicList=new ArrayList<Topic>(){
        {
            add(new Topic(1,1,WidgetService.widgetList.stream().filter(x->x.getTopicId()==1)
                    .collect(Collectors.toList()), "Topic1"));
        }
    };
    @PostMapping(path = "/api/lesson/{lid}/topic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic createTopic(@PathVariable("mid")Integer mid,
                               @RequestBody Topic t) {
        Topic newLesson=new Topic(t.getId(),mid,t.getWidgets(),t.getTitle());
        TopicService.topicList.add(newLesson);
        return t;
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid")Integer lid,
                                       HttpSession session) {
        return TopicService.topicList.stream().filter(x->x.getLessonId()
                == lid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/Topic/{lid}")
    public Topic findTopicById(
            @PathVariable("lid") Integer id) {
        for(Topic ls: TopicService.topicList) {
            if(id == ls.getId())
                return ls;
        }
        return null;
    }

    @PutMapping(path = "/api/Topic/{tid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic updateTopic (@PathVariable("tid")Integer id,
                                @RequestBody Topic t) {
        Optional<Topic> x= TopicService.topicList.stream().filter(e->e.getId()==id)
                .findFirst();
        if(x.isPresent())
            x.ifPresent(y->{
                y.setLessonId(t.getLessonId());
                y.setTitle(t.getTitle());
                y.setWidgets(t.getWidgets());
            });
        return t;
    }

    @DeleteMapping(" /api/topic/{tid}")
    public void deleteTopic(
            @PathVariable("tid") Integer id) {
        TopicService.topicList.removeIf(x->x.getId()==id);
    }
}
