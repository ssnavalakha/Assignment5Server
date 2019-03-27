package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.Lesson;
import com.example.assignment5.model.Module;
import com.example.assignment5.model.Topic;
import com.example.assignment5.repositories.LessonRepository;
import com.example.assignment5.repositories.ModuleRepository;
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
public class LessonService {

    @Autowired
    LessonRepository repo;

    @Autowired
    ModuleRepository mrepo;

    @Autowired
    TopicRepository trepo;

    @Autowired
    WidgetRepository wrepo;

    @PostMapping(path = "/api/module/{mid}/lesson", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson createLesson(@PathVariable("mid")long mid,
                               @RequestBody Lesson ls) {
        Lesson newLesson=new Lesson(mrepo.findById(mid).get(),ls.getId(),new ArrayList<Topic>(),ls.getTitle());
        repo.save(newLesson);
        return newLesson;
    }

    @GetMapping("/api/lessons")
    public List<Lesson> findAllLessons(HttpSession session) {
        List<Lesson> all=new ArrayList<Lesson>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid")long mid,
                                       HttpSession session) {
        return repo.findLessonByMid(mid);
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(
            @PathVariable("lid") long id) {
        Optional<Lesson> l=repo.findById(id);
        if(l.isPresent())
            return l.get();
        return null;
    }

    @PutMapping(path = "/api/lesson/{lid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson updateLesson (@PathVariable("lid")long id,
                                @RequestBody Module m) {
        Optional<Lesson> x= repo.findById(id);
        final Lesson[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                Lesson temp2=m.getLessons().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                //y.setModule(temp2.getModule());
                y.setTitle(temp2.getTitle());
                //y.setTopics(temp2.getTopics());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(
            @PathVariable("lid") long id) {
        List<Topic> t=trepo.findTopicByLid(id);
        for (int i=0;i<t.size();i++) {
            wrepo.deleteByTopicId(t.get(i).getId());
        }
        trepo.deleteByLessonId(id);
       repo.deleteById(id);
    }
}
