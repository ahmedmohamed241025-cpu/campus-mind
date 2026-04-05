package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import com.example.model.Status;

import java.time.LocalDateTime;



    public class AttendanceDto {

        // ===== AttendanceRequest (تسجيل حضور) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MarkRequest {

            @NotNull(message = "Student ID is required")
            private Long studentId;

            @NotNull(message = "Session ID is required")
            private Long sessionId;
        }

        // ===== AttendanceRequest (إنشاء حضور) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class AttendanceRequest {

            @NotNull(message = "Status is required")
            private Status status;

            @NotNull(message = "Student ID is required")
            private Long studentId;

            @NotNull(message = "Session ID is required")
            private Long sessionId;
        }

        // ===== AttendanceResponse (الرد للعميل) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class AttendanceResponse {
            private Long id;
            private Status status;
            private LocalDateTime timestamp;
            private Long studentId;
            private String studentName;
            private Long sessionId;
            private String sessionName;
        }

        // ===== AttendanceUpdateRequest (تحديث الحضور) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class AttendanceUpdateRequest {

            @NotNull(message = "Status is required")
            private Status status;
        }
    }

