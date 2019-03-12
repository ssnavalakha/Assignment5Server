package com.example.assignment5.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    private long facultyId;
    @Id
    private long id;
    @OneToMany(mappedBy = "course")
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
