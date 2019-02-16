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
public class LessonService {

    static ArrayList<Lesson> lessonList=new ArrayList<Lesson>(){
        {
            add(new Lesson(1,1,TopicService.topicList.stream().filter(s->s.getLessonId()==1)
                    .collect(Collectors.toList()), "Lesson1"));
        }
    };

    @PostMapping(path = "/api/module/{mid}/lesson", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson createLesson(@PathVariable("mid")long mid,
                               @RequestBody Lesson ls) {
        Lesson newLesson=new Lesson(mid,ls.getId(),new ArrayList<Topic>(),ls.getTitle());
        LessonService.lessonList.add(newLesson);
        Module parentModule=CourseService.UpdateParentModule(newLesson,mid,0);
        Course parentCourse=CourseService.UpdateParentCourse(parentModule,parentModule.getCourseId(),1);
        return newLesson;
    }

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid")long mid,
                                       HttpSession session) {
        return LessonService.lessonList.stream().filter(x->x.getModuleId()
                == mid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(
            @PathVariable("lid") long id) {
        for(Lesson ls: LessonService.lessonList) {
            if(id == ls.getId())
                return ls;
        }
        return null;
    }

    @PutMapping(path = "/api/lesson/{lid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Lesson updateLesson (@PathVariable("lid")long id,
                                @RequestBody Module m) {
        Optional<Lesson> x= LessonService.lessonList.stream().filter(t->t.getId()==id)
                .findFirst();
        final Lesson[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                Lesson temp2=m.getLessons().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                y.setModuleId(temp2.getModuleId());
                y.setTitle(temp2.getTitle());
                y.setTopics(temp2.getTopics());
                Module parentMpdule=CourseService.UpdateParentModule(y,y.getModuleId(),1);
                Course parentCourse=CourseService.UpdateParentCourse(parentMpdule,parentMpdule.getCourseId(),1);
            });
        return temp[0];
    }

    @DeleteMapping("/api/lesson/{lid}")
    public void deleteLesson(
            @PathVariable("lid") long id) {
        Lesson lessonToBeDeleted=LessonService.lessonList.stream().filter(t->t.getId()==id)
                .collect(Collectors.toList()).get(0);

        Module parentMpdule=CourseService.UpdateParentModule(lessonToBeDeleted,
                lessonToBeDeleted.getModuleId(),2);
        Course parentCourse=CourseService.UpdateParentCourse(parentMpdule,parentMpdule.getCourseId(),1);
        LessonService.lessonList.removeIf(x->x.getId()==id);
    }
}
