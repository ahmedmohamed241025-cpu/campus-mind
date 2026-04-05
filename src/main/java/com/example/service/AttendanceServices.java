package com.example.service;

import com.example.dto.AttendanceDto;
import com.example.entity.Attendance;
import com.example.entity.Session;
import com.example.entity.Student;
import com.example.model.Status;
import com.example.repository.AttendanceRepository;
import com.example.repository.SessionRepository;
import com.example.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServices {

    private final AttendanceRepository repo;
    private final SessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    // <<<<<<<<<<<<<<< MarkRequest → Entity>>>>>>>>>>>>>>.
    private Attendance convertToEntityForMark(AttendanceDto.MarkRequest dto) {
        Attendance attendance = new Attendance();

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found: " + dto.getStudentId()));

        Session session = sessionRepo.findById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found: " + dto.getSessionId()));

        attendance.setStudent(student);
        attendance.setSession(session);
        attendance.setStatus(Status.PRESENT);
        attendance.setTimestamp(LocalDateTime.now());

        return attendance;
    }

    // <<<<<<<<<<<< AttendanceRequest → Entity>>>>>>>>>>>>>>
    private Attendance convertToEntity(AttendanceDto.AttendanceRequest dto) {
        Attendance attendance = new Attendance();

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found: " + dto.getStudentId()));

        Session session = sessionRepo.findById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found: " + dto.getSessionId()));

        attendance.setStatus(dto.getStatus());
        attendance.setStudent(student);
        attendance.setSession(session);
        attendance.setTimestamp(LocalDateTime.now());

        return attendance;
    }

    // <<<<<<<<<<<< Entity → Response>>>>>>>>>>>>>>>>
    private AttendanceDto.AttendanceResponse convertToResponse(Attendance attendance) {
        AttendanceDto.AttendanceResponse response = new AttendanceDto.AttendanceResponse();
        response.setId(attendance.getId());
        response.setStatus(attendance.getStatus());
        response.setTimestamp(attendance.getTimestamp());

        if (attendance.getStudent() != null) {
            response.setStudentId(attendance.getStudent().getId());
            response.setStudentName(attendance.getStudent().getName());
        }

        if (attendance.getSession() != null) {
            response.setSessionId(attendance.getSession().getId());
            response.setSessionName(attendance.getSession().getName());
        }

        return response;
    }

    //  MARK
    public AttendanceDto.AttendanceResponse mark(AttendanceDto.MarkRequest request) {
        if (repo.findByStudent_IdAndSession_Id(request.getStudentId(), request.getSessionId()).isPresent()) {
            throw new RuntimeException("Attendance already recorded");
        }
        return convertToResponse(repo.save(convertToEntityForMark(request)));
    }

    //  CREATE
    public AttendanceDto.AttendanceResponse createAttendance(AttendanceDto.AttendanceRequest request) {
        return convertToResponse(repo.save(convertToEntity(request)));
    }

    //GET ALL
    public List<AttendanceDto.AttendanceResponse> getAllAttendance() {
        return repo.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    //  GET BY ID
    public AttendanceDto.AttendanceResponse getById(Long id) {
        Attendance attendance = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        return convertToResponse(attendance);
    }

    //  UPDATE
    public AttendanceDto.AttendanceResponse update(Long id, AttendanceDto.AttendanceUpdateRequest request) {
        Attendance attendance = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));

        attendance.setStatus(request.getStatus());
        attendance.setTimestamp(LocalDateTime.now());

        return convertToResponse(repo.save(attendance));
    }

    //  DELETE
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Attendance not found with id: " + id);
        }
        repo.deleteById(id);
    }
}