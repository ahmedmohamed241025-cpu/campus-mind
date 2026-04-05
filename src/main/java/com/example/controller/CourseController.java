package com.example.controller;

import com.example.dto.CourseDto;
import com.example.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")

public class CourseController {

    private final CourseService courseService;

    public CourseController (CourseService courseService) {
        this.courseService= courseService;
    }
// CREATE

    @PostMapping("/create")
    public ResponseEntity<CourseDto.CourseResponse> createCourse(
            @Valid @RequestBody CourseDto.CourseRequest request) {
        return new ResponseEntity<>(courseService.createCourse(request), HttpStatus.CREATED);
    }
    // GET ALL
    @GetMapping
    public ResponseEntity<List<CourseDto.CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto.CourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto.CourseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CourseDto.CourseUpdateRequest request) {
        return ResponseEntity.ok(courseService.update(id, request));
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
