package com.example.assignment5.service;

import com.example.assignment5.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseService {
    static ArrayList<Course> courses = new ArrayList<Course>() {
        {
            add(new Course(0,1,ModuleService.moduleList.stream().filter(x->(x.getCourseId()==1))
                    .collect(Collectors.toList()), "CS5610"));
        }
    };
    @PostMapping(path = "/api/courses",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Course createCourse(@RequestBody Course crs, HttpSession session) {
        crs.setFacultyId(((User)session.getAttribute("currentUser")).getId());

        return crs;
    }
    @GetMapping("/api/courses")
    public List<Course> findAllCourses(HttpSession session) {
        List<Course> cl = CourseService.courses.stream().filter(x->x.getFacultyId()
                ==
                ((User)session.getAttribute("currentUser")).getId())
                .collect(Collectors.toList());
        return cl;
    }

    @GetMapping("/api/courses/{id}")
    public Course findCourseById(
            @PathVariable("id") Integer id) {
        for(Course crs: CourseService.courses) {
            if(id == crs.getId())
                return crs;
        }
        return null;
    }

    @PutMapping(path = "/api/courses/{cid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Course updateCourse(@PathVariable("cid")Integer id,
                               @RequestBody Course crs) {
        Optional<Course> x= CourseService.courses.stream().filter(t->t.getId()==id).findFirst();
        if(x.isPresent())
            x.ifPresent(y->{
                y.setModules(crs.getModules());
                y.setFacultyId(crs.getFacultyId());
                y.setTitle(crs.getTitle());
            });
        return crs;
    }
    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(
            @PathVariable("cid") Integer id) {
        CourseService.courses.removeIf(x->x.getId()==id);
    }

    static Lesson UpdateParentLesson(Topic t,long lid,int action)
    {
        Lesson parentLesson=LessonService.lessonList.stream().filter(z->z.getId()==lid)
                .collect(Collectors.toList()).get(0);
        List<Topic> tl=tl=parentLesson.getTopics();;
        if(action==0)
        {
            tl.add(t);
        }
        if(action==1)
        {
            Topic oldTopic=tl.stream().filter(z->z.getId()==t.getId())
                    .collect(Collectors.toList()).get(0);
            tl.remove(oldTopic);
            tl.add(t);
        }
        if(action==2)
        {
            Topic oldTopic=tl.stream().filter(z->z.getId()==t.getId())
                    .collect(Collectors.toList()).get(0);
            tl.remove(oldTopic);
        }
        parentLesson.setTopics(tl);
        return parentLesson;
    }

    static Module UpdateParentModule(Lesson ls,long mid,int action)
    {
        Module parentModule=ModuleService.moduleList.stream().filter(z->z.getId()==mid)
                .collect(Collectors.toList()).get(0);
        List<Lesson> ll=parentModule.getLessons();
        if(action==0)
        {
            ll.add(ls);
        }
        if(action==1)
        {
            Lesson oldLesson=ll.stream().filter(z->z.getId()==ls.getId())
                    .collect(Collectors.toList()).get(0);
            ll.remove(oldLesson);
            ll.add(ls);
        }
        if(action==2)
        {
            Lesson oldLesson=ll.stream().filter(z->z.getId()==ls.getId())
                    .collect(Collectors.toList()).get(0);
            ll.remove(oldLesson);
        }
        parentModule.setLessons(ll);
        return parentModule;
    }

    static Course UpdateParentCourse(Module md,long cid,int action)
    {
        Course crs=CourseService.courses.stream().filter(z->z.getId()==cid)
                .collect(Collectors.toList()).get(0);
        List<Module>ml=crs.getModules();
        if(action==0)
        {
            ml.add(md);
        }
        if(action==1)
        {
            Module oldModule=ml.stream().filter(z->z.getId()==md.getId())
                    .collect(Collectors.toList()).get(0);
            ml.remove(oldModule);
            ml.add(md);
        }
        if(action==2)
        {
            Module oldModule=ml.stream().filter(z->z.getId()==md.getId())
                    .collect(Collectors.toList()).get(0);
            ml.remove(oldModule);
        }
        crs.setModules(ml);
        return crs;
    }
}
