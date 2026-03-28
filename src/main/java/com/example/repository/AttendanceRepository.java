package com.example.repository;

import com.example.entity.Attendance;
import com.example.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByStudent_IdAndSession_Id(Long studentId, Long sessionId);
}