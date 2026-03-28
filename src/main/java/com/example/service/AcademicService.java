package com.example.service;
import com.example.entity.*;
import com.example.repository.*;
import com.example.entity.Course;
import com.example.entity.Location;
import com.example.entity.Session;
import com.example.repository.CourseRepository;
import com.example.repository.LocationRepository;
import com.example.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicService {
    private final CourseRepository courseRepository;
    private final LocationRepository locationRepository;
    private final SessionRepository sessionRepository;

    public AcademicService(CourseRepository courseRepository, LocationRepository locationRepository, SessionRepository sessionRepository) {
        this.courseRepository = courseRepository;
        this.locationRepository = locationRepository;
        this.sessionRepository = sessionRepository;
    }

    public Course saveCourse(Course course) { return courseRepository.save(course); }
    public Location saveLocation(Location location) { return locationRepository.save(location); }
    public Session saveSession(Session session) { return sessionRepository.save(session); }
}