package com.example.assignment5.service;

import com.example.assignment5.model.TYPE;
import com.example.assignment5.model.Widget;
import com.example.assignment5.model.WidgetItem;
import com.example.assignment5.repositories.TopicRepository;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class WidgetService {

    @Autowired
    WidgetRepository repo;

    @GetMapping("/api/widget/{wid}")
    public Widget findWidgetById(
            @PathVariable("wid") long id) {
        Optional<Widget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets(HttpSession session) {
        List<Widget> all=new ArrayList<Widget>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @PutMapping(path = "/api/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Widget updateWidget (@PathVariable("wid")long id,
                              @RequestBody Widget w) {
        Optional<Widget> x= repo.findById(id);
        final Widget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDown(w.getDown());
                y.setPosition(w.getPosition());
                //y.setTopic(w.getTopic());
                y.setUp(w.getUp());
                y.setType(w.getType());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
