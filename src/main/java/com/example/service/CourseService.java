package com.example.service;

import com.example.dto.CourseDto;
import com.example.entity.Course;
import com.example.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository repo;

    //  DTO → Entity
    private Course convertToEntity(CourseDto.CourseRequest dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        return course;
    }

    //  Entity → DTO Response
    private CourseDto.CourseResponse convertToResponse(Course course) {
        CourseDto.CourseResponse response = new CourseDto.CourseResponse();
        response.setId(course.getId());
        response.setName(course.getName());
        response.setCode(course.getCode());
        return response;
    }

    //  CREATE
    public CourseDto.CourseResponse createCourse(CourseDto.CourseRequest request) {
        Course course = convertToEntity(request);
        Course savedCourse = repo.save(course);
        return convertToResponse(savedCourse);
    }

    //  GET ALL
    public List<CourseDto.CourseResponse> getAllCourses() {
        return repo.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    //  GET BY ID
    public CourseDto.CourseResponse getById(Long id) {
        Course course = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return convertToResponse(course);
    }

    //  UPDATE
    public CourseDto.CourseResponse update(Long id, CourseDto.CourseUpdateRequest request) {
        Course course = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        course.setName(request.getName());
        Course updatedCourse = repo.save(course);
        return convertToResponse(updatedCourse);
    }

    //  DELETE
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        repo.deleteById(id);
    }
}