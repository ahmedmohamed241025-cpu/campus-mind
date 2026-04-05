package com.example.controller;

import com.example.dto.LocationDto;
import com.example.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    //  CREATE
    @PostMapping("/create")
    public ResponseEntity<LocationDto.LocationResponse> createLocation(
            @Valid @RequestBody LocationDto.LocationRequest request) {
        return new ResponseEntity<>(service.createLocation(request), HttpStatus.CREATED);
    }

    //  GET ALL
    @GetMapping
    public ResponseEntity<List<LocationDto.LocationResponse>> getAllLocations() {
        return ResponseEntity.ok(service.getAllLocations());
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto.LocationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LocationDto.LocationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody LocationDto.LocationUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



