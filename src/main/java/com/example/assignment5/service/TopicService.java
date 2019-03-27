package com.example.assignment5.service;

import com.example.assignment5.model.*;
import com.example.assignment5.repositories.*;
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
public class TopicService {
    @Autowired
    TopicRepository repo;
    @Autowired
    LessonRepository lrepo;
    @Autowired
    WidgetRepository wrepo;
    @Autowired
    HeadingWidgetRepository hrepo;
    @Autowired
    ParagraphWidgetRepository prepo;
    @Autowired
    LinkWidgetRepository linkrepo;
    @Autowired
    ListWidgetRepository listRepo;
    @Autowired
    ImageWidgetRepository iRepo;


    @PostMapping(path = "/api/lesson/{lid}/topic", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic createTopic(@PathVariable("lid")long lid,
                             @RequestBody Topic t) {
        Topic newTopic=new Topic(t.getId(),lrepo.findById(lid).get(),t.getWidgets(),t.getTitle());
        repo.save(newTopic);
        return newTopic;
    }

    @PostMapping(path = "/api/topic/{tid}/widget", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public WidgetItem createWidget(@PathVariable("tid")long tid
                               ,@RequestBody WidgetItem w) {

        if(w.getType().equals("HEADING"))
            hrepo.save(new HeadingWidget(w.getId(),repo.findById(tid).get(),w.getPosition(),w.getUp()
                    ,w.getDown(),w.getType(),w.getSize(),w.getText()));
        if(w.getType().equals("LIST"))
            listRepo.save(new ListWidget(w.getId(),repo.findById(tid).get(),w.getPosition(),w.getUp()
                    ,w.getDown(),w.getType(),w.getItems(),w.getDdType()));
        if(w.getType().equals("LINK"))
            linkrepo.save(new LinkWidget(w.getId(),repo.findById(tid).get(),w.getPosition(),w.getUp()
                    ,w.getDown(),w.getType(),w.getHref(),w.getTitle()));
        if(w.getType().equals("IMAGE"))
            iRepo.save(new ImageWidget(w.getId(),repo.findById(tid).get(),w.getPosition(),w.getUp()
                    ,w.getDown(),w.getType(),w.getSrc()));
        if(w.getType().equals("PARAGRAPH"))
            prepo.save(new ParagraphWidget(w.getId(),repo.findById(tid).get(),w.getPosition(),w.getUp()
                    ,w.getDown(),w.getType(),w.getText()));
        return w;
    }

    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid")long lid,
                                     HttpSession session) {
        return repo.findTopicByLid(lid);
    }

    @GetMapping("/api/topics")
    public List<Topic> findAllTopics(HttpSession session) {
        List<Topic> all=new ArrayList<Topic>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgetsForTopic(@PathVariable("tid")long tid,
                                     HttpSession session) {
        return wrepo.findWidgetByTid(tid);
    }

    @GetMapping("/api/topic/widgets")
    public List<Object> findAllWidgets(HttpSession session) {
        List<Object> wids=new ArrayList<Object>();
        hrepo.findAll().forEach(x->wids.add(new WidgetReturnStructure(x)));
        iRepo.findAll().forEach(x->wids.add(new WidgetReturnStructure(x)));
        linkrepo.findAll().forEach(x->wids.add(new WidgetReturnStructure(x)));
        listRepo.findAll().forEach(x->wids.add(new WidgetReturnStructure(x)));
        prepo.findAll().forEach(x->wids.add(new WidgetReturnStructure(x)));
        return wids;
    }

    @GetMapping("/api/topic/{tid}")
    public Topic findTopicById(
            @PathVariable("tid") long id) {
        Optional<Topic> t=repo.findById(id);
        if(t.isPresent())
            return t.get();
        return null;
    }

    @PutMapping(path = "/api/topic/{tid}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Topic updateTopic (@PathVariable("tid")long id,
                              @RequestBody Lesson l) {
        Optional<Topic> x= repo.findById(id);
        final Topic[] temp = {null};
        if(x.isPresent())
            x.ifPresent(y->{
                temp[0] =y;
                Topic temp2=l.getTopics().stream().filter(z->z.getId()==id)
                        .collect(Collectors.toList()).get(0);
                //y.setLesson(temp2.getLesson());
                y.setTitle(temp2.getTitle());
                //y.setWidgets(temp2.getWidgets());
                repo.save(y);
                });
        return temp[0];
    }

    @DeleteMapping("/api/topic/{tid}")
    public void deleteTopic(
            @PathVariable("tid") long id) {
        wrepo.deleteByTopicId(id);
        repo.deleteById(id);
    }
}
