package com.example.service;

import com.example.entity.Attendance;
import com.example.entity.Session;
import com.example.entity.Student;
import com.example.model.Status;
import com.example.repository.AttendanceRepository;
import com.example.repository.SessionRepository;
import com.example.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceServices {

    private final AttendanceRepository repo;
    private final SessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    public Attendance mark(Long studentId, Long sessionId) {
        if(
                repo.findByStudent_IdAndSession_Id(studentId, sessionId).isPresent()
        ){
            throw new RuntimeException("Attendance already recorded");
        }
        Student student = studentRepo.findById(studentId).orElseThrow(() ->new RuntimeException("Student not found"));

        Session session = sessionRepo.findById(sessionId).orElseThrow(() ->new RuntimeException("Session not found"));

        Attendance attendance = new Attendance();

        attendance.setSession(session);
        attendance.setStudent(student);
        attendance.setStatus(Status.PRESENT);
        attendance.setTimestamp(LocalDateTime.now());

        return repo.save(attendance);
    }
}
