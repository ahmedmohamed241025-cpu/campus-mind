package com.example.controller;

import com.example.entity.Attendance;
import com.example.service.AttendanceServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendace")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServices services;

    public Attendance mark(@RequestParam Long studentId, @RequestParam Long sessionId){
        return services.mark(studentId, sessionId);
    }

}
