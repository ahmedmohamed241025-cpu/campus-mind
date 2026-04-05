package com.example.dto;


import com.example.model.SessionType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class SessionDto {

    // <<<<<<<<<< SessionRequest (البيانات الجاية من الطالب)>>>>>>>>>>
    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class SessionRequest{
             //<<<<<<<<<Validation>>>>>>>>>>>>>>
        @NotNull(message = "Session type is required")
        private SessionType type;  // Lecture, Lab, Exam...

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        private LocalDateTime startTime;

        @NotNull(message = "End time is required")
        @Future(message = "End time must be in the future")
        private LocalDateTime endTime;

        @NotNull(message = "Course ID is required")
        private Long courseId;

        @NotNull(message = "Instructor ID is required")
        private Long instructorId;

        @NotNull(message = "Location ID is required")
        private Long locationId;

        @NotBlank(message = "Session name cannot be blank")
        @Size(min = 3, max = 100, message = "Session name must be between 3 and 100 characters")
        private String name;
    }
    // <<<<<<<<<<<<<<<<<<< SessionResponse (البيانات هترجع للطالب) >>>>>>>>>>>>
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionResponse {
        private Long id;
        private SessionType type;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Long courseId;
        private String courseName;  // اسم الكورس للعرض
        private Long instructorId;
        private String instructorName;  // اسم المحاضر للعرض
        private Long locationId;
        private String locationName;  // اسم المكان للعرض
        private String name;
    }
    // ===== SessionUpdateRequest (تحديث الجلسة) =====
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionUpdateRequest {

        private SessionType type;

        @Future(message = "Start time must be in the future")
        private LocalDateTime startTime;

        @Future(message = "End time must be in the future")
        private LocalDateTime endTime;

        @Size(min = 3, max = 100, message = "Session name must be between 3 and 100 characters")
        private String name;
    }
}




