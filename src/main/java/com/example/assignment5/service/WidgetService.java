package com.example.assignment5.service;

import com.example.assignment5.model.TYPE;
import com.example.assignment5.model.Widget;
import com.example.assignment5.model.WidgetItem;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WidgetService {
    static ArrayList<WidgetItem> widgetList=new ArrayList<WidgetItem>(){
        {
            add(new WidgetItem(1,new Widget(1,1,1,"Hello",null,null,null,
                    "First",0,0,0,0,TYPE.HEADING.toString())));
            add(new WidgetItem(1,new Widget(2,1,1,"HI",null,null,null,
                    "Hello2",0,1,0,0,TYPE.HEADING.toString())));
        }
    };
}
