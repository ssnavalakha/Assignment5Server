package com.example.assignment5.service;

import com.example.assignment5.model.Course;
import com.example.assignment5.model.Module;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleService {

    static ArrayList<Module> moduleList=new ArrayList<Module>(){
        {
            add(new Module(1,1,LessonService.lessonList.stream().filter(x->x.getModuleId()==1)
                    .collect(Collectors.toList()),"Module1"));
        }
    };

    @PostMapping(path = "/api/courses/{cid}/modules", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module createModule(@PathVariable("cid")Integer cid,
                               @RequestBody Module md) {
        Module newModule=new Module(md.getId(),cid,md.getLessons(),md.getTitle());
        ModuleService.moduleList.add(newModule);
        return md;
    }

    @GetMapping("/api/course/{cid}/modules")
    public List<Module> findAllModules(@PathVariable("cid")Integer cid,
                                       HttpSession session) {
        return ModuleService.moduleList.stream().filter(x->x.getCourseId()
                == cid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(
            @PathVariable("mid") Integer id) {
        for(Module md: ModuleService.moduleList) {
            if(id == md.getId())
                return md;
        }
        return null;
    }

    @PutMapping(path = "/api/modules/{mid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module updateCourse(@PathVariable("mid")Integer id,
                               @RequestBody Module md) {
        Optional<Module> x= ModuleService.moduleList.stream().filter(t->t.getId()==id).findFirst();
        if(x.isPresent())
            x.ifPresent(y->{
                y.setCourseId(md.getCourseId());
                y.setLessons(md.getLessons());
                y.setTitle(md.getTitle());
            });
        return md;
    }
    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(
            @PathVariable("mid") Integer id) {
        ModuleService.moduleList.removeIf(x->x.getId()==id);
    }
}
