package com.example.assignment5.service;

import com.example.assignment5.model.LinkWidget;
import com.example.assignment5.repositories.LinkWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LinkWidgetService {
    @Autowired
    LinkWidgetRepository repo;

    @GetMapping("/api/link/widget/{wid}")
    public LinkWidget findWidgetById(
            @PathVariable("wid") long id) {
        Optional<LinkWidget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/link/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LinkWidget updateWidget (@PathVariable("wid")long id,
                                @RequestBody LinkWidget w) {
        Optional<LinkWidget> x= repo.findById(id);
        final LinkWidget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDown(w.getDown());
                y.setHref(w.getHref());
                y.setPosition(w.getPosition());
                y.setTopic(w.getTopic());
                y.setTitle(w.getTitle());
                y.setUp(w.getUp());
                y.setType(w.getType());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/link/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
