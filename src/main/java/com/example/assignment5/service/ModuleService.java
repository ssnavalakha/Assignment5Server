package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.Module;
import com.example.assignment5.model.User;
import com.example.assignment5.repositories.CourseRepository;
import com.example.assignment5.repositories.ModuleRepository;
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
public class ModuleService {

    @Autowired
    ModuleRepository repo;
    @Autowired
    CourseRepository crepo;

    @PostMapping(path = "/api/courses/{cid}/modules", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module createModule(@PathVariable("cid")long cid,
                               @RequestBody Module md) {
        Module newModule=new Module(md.getId(),crepo.findById(cid).get(),md.getLessons(),md.getTitle());
        repo.save(newModule);
        return newModule;
    }

    @GetMapping("/api/course/{cid}/modules")
    public List<Module> findAllModules(@PathVariable("cid")long cid,
                                       HttpSession session) {

        return repo.findModulesByCid(cid);
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(
            @PathVariable("mid") long id) {
        Optional<Module> md=repo.findById(id);
        if(md.isPresent())
        {
            return md.get();
        }
        return null;
    }

    @PutMapping(path = "/api/modules/{mid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module updateModule(@PathVariable("mid")long id,
                               @RequestBody Course crs) {
        Optional<Module> x= repo.findById(id);
        final Module[] temp2 = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp2[0] =y;
                Module temp=crs.getModules().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                y.setCourse(temp.getCourse());
                y.setLessons(temp.getLessons());
                y.setTitle(temp.getTitle());
                repo.save(y);
            });
        return temp2[0];
    }
    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(
            @PathVariable("mid") long id) {
        repo.deleteById(id);
    }
}
