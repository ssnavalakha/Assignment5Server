package com.example.assignment5.service;

import com.example.assignment5.model.Lesson;
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
public class LessonService {

    static ArrayList<Lesson> lessonList=new ArrayList<Lesson>(){
        {
            add(new Lesson(1,1,TopicService.topicList.stream().filter(s->s.getLessonId()==1)
                    .collect(Collectors.toList()), "Lesson1"));
        }
    };

    @PostMapping(path = "/api/module/{mid}/lesson", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson createLesson(@PathVariable("mid")Integer mid,
                               @RequestBody Lesson ls) {
        Lesson newLesson=new Lesson(ls.getId(),mid,ls.getTopics(),ls.getTitle());
        LessonService.lessonList.add(newLesson);
        return ls;
    }

    @GetMapping(" /api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid")Integer mid,
                                       HttpSession session) {
        return LessonService.lessonList.stream().filter(x->x.getModuleId()
                == mid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(
            @PathVariable("lid") Integer id) {
        for(Lesson ls: LessonService.lessonList) {
            if(id == ls.getId())
                return ls;
        }
        return null;
    }

    @PutMapping(path = "/api/lesson/{lid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson updateLesson (@PathVariable("lid")Integer id,
                               @RequestBody Lesson ls) {
        Optional<Lesson> x= LessonService.lessonList.stream().filter(t->t.getId()==id)
                .findFirst();
        if(x.isPresent())
            x.ifPresent(y->{
                y.setModuleId(ls.getModuleId());
                y.setTitle(ls.getTitle());
                y.setTopics(ls.getTopics());
            });
        return ls;
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(
            @PathVariable("lid") Integer id) {
        LessonService.lessonList.removeIf(x->x.getId()==id);
    }
}
