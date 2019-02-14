package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.Lesson;
import com.example.assignment5.model.Module;
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
public class TopicService {
    static ArrayList<Topic> topicList=new ArrayList<Topic>(){
        {
            add(new Topic(1,1,WidgetService.widgetList.stream().filter(x->x.getTopicId()==1)
                    .collect(Collectors.toList()), "Topic1"));
        }
    };
    @PostMapping(path = "/api/lesson/{lid}/topic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic createTopic(@PathVariable("lid")long lid,
                             @RequestBody Topic t) {
        Topic newTopic=new Topic(t.getId(),lid,t.getWidgets(),t.getTitle());
        TopicService.topicList.add(newTopic);
        Lesson parentLesson=CourseService.UpdateParentLesson(newTopic,lid,0);
        Module parentModule=CourseService.UpdateParentModule(parentLesson,parentLesson.getModuleId(),1);
        Course parentCOurse=CourseService.UpdateParentCourse(parentModule,parentModule.getCourseId(),1);
        return t;
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid")long lid,
                                     HttpSession session) {
        return TopicService.topicList.stream().filter(x->x.getLessonId()
                == lid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/Topic/{lid}")
    public Topic findTopicById(
            @PathVariable("lid") long id) {
        for(Topic ls: TopicService.topicList) {
            if(id == ls.getId())
                return ls;
        }
        return null;
    }

    @PutMapping(path = "/api/topic/{tid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic updateTopic (@PathVariable("tid")long id,
                              @RequestBody Lesson l) {
        Optional<Topic> x= TopicService.topicList.stream().filter(e->e.getId()==id)
                .findFirst();
        final Topic[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                Topic temp2=l.getTopics().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                y.setLessonId(temp2.getLessonId());
                y.setTitle(temp2.getTitle());
                y.setWidgets(temp2.getWidgets());
                Lesson parentLesson=CourseService.UpdateParentLesson(y,y.getLessonId(),1);
                Module parentModule=CourseService.UpdateParentModule(parentLesson,parentLesson.getModuleId(),1);
                Course parentCOurse=CourseService.UpdateParentCourse(parentModule,parentModule.getCourseId(),1);
            });
        return temp[0];
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(
            @PathVariable("tid") long id) {
        Topic topicTobeDeleted=TopicService.topicList.stream().filter(e->e.getId()==id)
                .collect(Collectors.toList()).get(0);
        Lesson parentLesson=CourseService.UpdateParentLesson(topicTobeDeleted,topicTobeDeleted.getLessonId(),2);
        Module parentModule=CourseService.UpdateParentModule(parentLesson,parentLesson.getModuleId(),1);
        Course parentCOurse=CourseService.UpdateParentCourse(parentModule,parentModule.getCourseId(),1);
        TopicService.topicList.removeIf(x->x.getId()==id);
    }
}
