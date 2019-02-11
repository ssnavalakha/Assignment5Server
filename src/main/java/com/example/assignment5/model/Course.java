package com.example.assignment5.model;

import java.util.List;

public class Course {
    private long facultyId;
    private long id;
    private List<Module> modules;
    private String title;

    public Course(){}

    public Course(long facultyId, long id, List<Module> modules, String title) {
        this.facultyId = facultyId;
        this.id = id;
        this.modules = modules;
        this.title = title;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getId() {
        return id;
    }

    public void setid(long courseId) {
        this.id = courseId;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
