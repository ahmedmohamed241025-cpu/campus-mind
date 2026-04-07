package com.example.controller;

import com.example.dto.AttendanceDto;
import com.example.entity.Attendance;
import com.example.service.AttendanceServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendace")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServices services;
    @PostMapping("/mark")
    public Attendance mark(@RequestParam Long studentId, @RequestParam Long sessionId){
        return services.mark(studentId, sessionId);
    }

    // CREATE
    @PostMapping("/create")
    public Attendance createAttendance(@RequestBody AttendanceDto attendance) {
        Attendance AttendanceDto = new Attendance();
        return services.createAttendance(AttendanceDto);
    }

    // GET ALL
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return services.getAllAttendance();
    }
    // GET BY ID
    @GetMapping("/{id}")
    public Attendance getById(@PathVariable Long id){
        return services.getById(id);
    }
    // UPDATE
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody AttendanceDto attendance){
        Attendance AttendanceDto = new Attendance();
        services.update(id, AttendanceDto);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        services.deleteById(id);
    }



}
