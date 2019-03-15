package com.example.assignment5.service;

import com.example.assignment5.model.ListWidget;
import com.example.assignment5.repositories.ListWidgetRepository;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ListWidgetService {
    @Autowired
    ListWidgetRepository repo;
    @Autowired
    WidgetRepository wrepo;

    @GetMapping("/api/list/widget/{wid}")
    public ListWidget findWidgetById(
            @PathVariable("wid") long id) {
        Optional<ListWidget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/list/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ListWidget updateWidget (@PathVariable("wid")long id,
                                @RequestBody ListWidget w) {
        Optional<ListWidget> x= repo.findById(id);
        final ListWidget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDdType(w.getDdType());
                y.setDown(w.getDown());
                y.setItems(w.getItems());
                y.setPosition(w.getPosition());
                //y.setTopic(w.getTopic());
                y.setUp(w.getUp());
                y.setType(w.getType());
                if(!y.getType().equals("LIST"))
                    wrepo.deleteById(y.getId());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/list/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
