package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repo;

    public Course createCourse(Course course) {
        return repo.save(course);
    }
}
