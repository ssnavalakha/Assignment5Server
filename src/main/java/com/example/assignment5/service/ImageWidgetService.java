package com.example.assignment5.service;

import com.example.assignment5.model.ImageWidget;
import com.example.assignment5.repositories.ImageWidgetRepository;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ImageWidgetService {
    @Autowired
    ImageWidgetRepository repo;
    @Autowired
    WidgetRepository wrepo;

    @GetMapping("/api/image/widget/{wid}")
    public ImageWidget findWidgetById(
            @PathVariable("wid") long id) {
        Optional<ImageWidget> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/image/widget/{wid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ImageWidget updateWidget (@PathVariable("wid")long id,
                                @RequestBody ImageWidget w) {
        Optional<ImageWidget> x= repo.findById(id);
        final ImageWidget[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                y.setDown(w.getDown());
                y.setPosition(w.getPosition());
                //y.setTopic(w.getTopic());
                y.setSrc(w.getSrc());
                y.setUp(w.getUp());
                y.setType(w.getType());
                if(!y.getType().equals("IMAGE"))
                    wrepo.deleteById(y.getId());
                repo.save(y);
            });
        return temp[0];
    }

    @DeleteMapping("/api/image/widget/{wid}")
    public void deleteWidget(
            @PathVariable("wid") long id) {
        repo.deleteById(id);
    }
}
