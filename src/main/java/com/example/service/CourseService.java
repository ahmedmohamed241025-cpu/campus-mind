package com.example.service;

import com.example.entity.Course;
import com.example.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repo;

    public Course createCourse(Course course) {
        return repo.save(course);
    }

    public Course getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Course not found with id:" + id));
    }

    public void update(Long id, Course course) {
        Course c= repo.findById(id).orElseThrow(()->new RuntimeException("Course not found with id :" + id));
        c.setName(course.getName());
        repo.save(c);
    }

    public void deleteById(Long id) {
        if ((!repo.existsById(id))){
            throw new RuntimeException("Course not found with id:" + id);
        }
        repo.deleteById(id);
    }
}
