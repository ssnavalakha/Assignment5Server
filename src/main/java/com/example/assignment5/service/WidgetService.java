package com.example.assignment5.service;

import com.example.assignment5.model.TYPE;
import com.example.assignment5.model.Widget;
import com.example.assignment5.model.WidgetItem;
import com.example.assignment5.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WidgetService {

    @Autowired
    WidgetRepository repo;
}
