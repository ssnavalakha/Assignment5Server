package com.example.assignment5.service;

import com.example.assignment5.model.*;
import com.example.assignment5.repositories.CourseRepository;
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
public class CourseService {
    @Autowired
    CourseRepository repo;


    @PostMapping(path = "/api/courses",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Course createCourse(@RequestBody Course crs, HttpSession session) {
        crs.setUser(((User)session.getAttribute("currentUser")));
        repo.save(crs);
        return crs;
    }
    @GetMapping("/api/courses")
    public List<Course> findAllCourses(HttpSession session) {
        List<Course> all=new ArrayList<Course>();
        repo.findAll().forEach(all::add);
        List<Course> cl = all.stream().filter(x->x.getUser().getId()
                ==
                ((User)session.getAttribute("currentUser")).getId())
                .collect(Collectors.toList());
        return cl;
    }

    @GetMapping("/api/courses/{id}")
    public Course findCourseById(
            @PathVariable("id") long id) {
        Optional<Course> crs=repo.findById(id);
        if(crs.isPresent())
        {
            return crs.get();
        }
        return null;
    }

    @PutMapping(path = "/api/courses/{cid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Course updateCourse(@PathVariable("cid")long id,
                               @RequestBody Course crs) {
        Optional<Course> x= repo.findById(id);
        if(x.isPresent())
            x.ifPresent(y->{
                y.setModules(crs.getModules());
                y.setUser(crs.getUser());
                y.setTitle(crs.getTitle());
                repo.save(y);
            });
        return crs;
    }
    @DeleteMapping("/api/courses/{cid}")
    public void deleteCourse(
            @PathVariable("cid") long id) {
        repo.deleteById(id);
    }
}
