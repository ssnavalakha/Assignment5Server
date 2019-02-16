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
public class ModuleService {

    static ArrayList<Module> moduleList=new ArrayList<Module>(){
        {
            add(new Module(1,1,LessonService.lessonList.stream().filter(x->x.getModuleId()==1)
                    .collect(Collectors.toList()),"Module1"));
        }
    };

    @PostMapping(path = "/api/courses/{cid}/modules", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module createModule(@PathVariable("cid")long cid,
                               @RequestBody Module md) {
        Module newModule=new Module(md.getId(),cid,md.getLessons(),md.getTitle());
        ModuleService.moduleList.add(newModule);
        Course parentCourse=CourseService.UpdateParentCourse(newModule,cid,0);
        return newModule;
    }

    @GetMapping("/api/course/{cid}/modules")
    public List<Module> findAllModules(@PathVariable("cid")long cid,
                                       HttpSession session) {
        return ModuleService.moduleList.stream().filter(x->x.getCourseId()
                == cid)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(
            @PathVariable("mid") long id) {
        for(Module md: ModuleService.moduleList) {
            if(id == md.getId())
                return md;
        }
        return null;
    }

    @PutMapping(path = "/api/modules/{mid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Module updateModule(@PathVariable("mid")long id,
                               @RequestBody Course crs) {
        Optional<Module> x= ModuleService.moduleList.stream().filter(t->t.getId()==id).findFirst();
        final Module[] temp2 = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp2[0] =y;
                Module temp=crs.getModules().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                y.setCourseId(temp.getCourseId());
                y.setLessons(temp.getLessons());
                y.setTitle(temp.getTitle());
                CourseService.UpdateParentCourse(y,y.getCourseId(),1);
            });
        return temp2[0];
    }
    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(
            @PathVariable("mid") long id) {
        Module moduleToBeDeleted=ModuleService.moduleList.stream()
                .filter(x->x.getId()==id).collect(Collectors.toList()).get(0);

        Course parentCourse=CourseService.UpdateParentCourse(moduleToBeDeleted,
                moduleToBeDeleted.getCourseId(),2);
        ModuleService.moduleList.removeIf(x->x.getId()==id);
    }
}
