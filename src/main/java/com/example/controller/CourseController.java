package com.example.controller;

import com.example.entity.Course;
import com.example.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService services;

    public CourseController (CourseService services) {
        this.services= services;
    }
// CREATE
    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {
        return services.createCourse(course);
    }

    // GET ALL
    @GetMapping
    public List<Course> getAllCourses() {
        return services.getAllCourses();
    }
    // GET BY ID
    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id){
        return services.getById(id);
    }
    // UPDATE
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Course course){
        services.update(id, course);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        services.deleteById(id);
    }
}
