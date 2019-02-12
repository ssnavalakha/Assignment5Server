package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.User;
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
        CourseService.courses.add(crs);
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
}
