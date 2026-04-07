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
import java.util.List;

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
        attendance.setTimestamp(LocalDateTime.now());// عيين وقت التسجيل إلى الوقت الحالي

        return repo.save(attendance);
    }


    // CREATE
    public Attendance createAttendance(Attendance attendance) {
        return repo.save(attendance);
    }
    // GET ALL
    // هات كل سجلات الحضور
    public List<Attendance> getAllAttendance() {
        return repo.findAll();
    }

    // GET BY ID
    public Attendance getById(Long id) { // هات حضور محدد ب ID
        return repo.findById(id) // البحث عن الحضور في قاعدة البيانات
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id)); //  لو مفيش ابعت رساله خطأ
    }

    // UPDATE
    public void update(Long id, Attendance attendance) {
        Attendance a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        a.setStatus(attendance.getStatus()); // تحديث حالة الحضور (حاضر/غائب)
        a.setTimestamp(attendance.getTimestamp()); // تحديث وقت تسجيل الحضور
        a.setStudent(attendance.getStudent()); // تحديث الطالب
        a.setSession(attendance.getSession()); // تحديث الجلسة

        repo.save(a); // احفظ التحديثات في قاعدة البيانات
    }

    // DELETE
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Attendance not found with id: " + id);
        }
        repo.deleteById(id);
    }
}