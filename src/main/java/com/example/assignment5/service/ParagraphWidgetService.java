package com.example.assignment5.service;

import com.example.assignment5.model.ParagraphWidget;
import com.example.assignment5.repositories.ParagraphWidgetRepository;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ParagraphWidgetService {
    @Autowired
    ParagraphWidgetRepository repo;

    @GetMapping("/api/paragraph/widget/{wid}")
    public ParagraphWidget findWidgetById(
            @PathVariable("wid") long id) {
        Optional<ParagraphWidget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/paragraph/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ParagraphWidget updateWidget (@PathVariable("wid")long id,
                                @RequestBody ParagraphWidget w) {
        Optional<ParagraphWidget> x= repo.findById(id);
        final ParagraphWidget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDown(w.getDown());
                y.setPosition(w.getPosition());
                y.setText(w.getText());
                y.setTopic(w.getTopic());
                y.setUp(w.getUp());
                y.setType(w.getType());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/paragraph/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
