package com.example.controller;

import com.example.dto.AttendanceDto;
import com.example.service.AttendanceServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServices services;

    // MARK
    @PostMapping("/mark")
    public ResponseEntity<AttendanceDto.AttendanceResponse> mark(
            @Valid @RequestBody AttendanceDto.MarkRequest request) {
        return new ResponseEntity<>(services.mark(request), HttpStatus.CREATED);
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<AttendanceDto.AttendanceResponse> createAttendance(
            @Valid @RequestBody AttendanceDto.AttendanceRequest request) {
        return new ResponseEntity<>(services.createAttendance(request), HttpStatus.CREATED);
    }

    //  GET ALL
    @GetMapping
    public ResponseEntity<List<AttendanceDto.AttendanceResponse>> getAllAttendance() {
        return ResponseEntity.ok(services.getAllAttendance());
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto.AttendanceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(services.getById(id));
    }

    //  UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto.AttendanceResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceDto.AttendanceUpdateRequest request) {
        return ResponseEntity.ok(services.update(id, request));
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
