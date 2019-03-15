package com.example.assignment5.service;

import com.example.assignment5.model.HeadingWidget;
import com.example.assignment5.repositories.HeadingWidgetRepository;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HeadingWidgetService {
    @Autowired
    HeadingWidgetRepository repo;

    @Autowired
    WidgetRepository wrepo;

    @GetMapping("/api/heading/widget/{wid}")
    public HeadingWidget findHeadingWidgetById(@PathVariable("wid") long id){
        Optional<HeadingWidget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @GetMapping("/api/heading/widget/")
    public List<HeadingWidget> findAllHeadingWidget(){
        return (List<HeadingWidget>) repo.findAll();
    }

    @PutMapping(path = "/api/heading/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public HeadingWidget updateWidget (@PathVariable("wid")long id,
                                @RequestBody HeadingWidget w) {
        Optional<HeadingWidget> x= repo.findById(id);
        final HeadingWidget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDown(w.getDown());
                y.setPosition(w.getPosition());
                y.setSize(w.getSize());
                y.setText(w.getText());
                //y.setTopic(w.getTopic());
                y.setUp(w.getUp());
                y.setType(w.getType());
                if(!y.getType().equals("HEADING"))
                    wrepo.deleteById(y.getId());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/heading/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
