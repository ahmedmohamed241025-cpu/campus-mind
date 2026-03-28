package com.example.service;
import com.example.entity.*;
import com.example.repository.*;
import com.example.entity.Instructor;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.repository.InstructorRepository;
import com.example.repository.StudentRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public UserService(UserRepository userRepository, StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }

    public User saveUser(User user) { return userRepository.save(user); }
    public Student saveStudent(Student student) { return studentRepository.save(student); }
    public Instructor saveInstructor(Instructor instructor) { return instructorRepository.save(instructor); }
}