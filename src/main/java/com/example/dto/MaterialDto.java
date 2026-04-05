package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MaterialDto {



        // >>>>>>>>>>MaterialRequest (إنشاء مادة جديدة) <<<<<<<<<<<<<<<<<>>>>>>>>
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MaterialRequest {

            @NotBlank(message = "Material title cannot be blank")
            @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
            private String title;

            @Pattern(regexp = "^(http|https)://.*$", message = "PDF URL must be a valid URL")
            private String pdfUrl;

            @Pattern(regexp = "^(http|https)://.*$", message = "Video URL must be a valid URL")
            private String videoUrl;

            @NotNull(message = "Course ID is required")
            private Long courseId;
        }

        // ===== MaterialResponse (الرد للطالب) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MaterialResponse {
            private Long id;
            private String title;
            private String pdfUrl;
            private String videoUrl;
            private Long courseId;
            private String courseName;
        }

        // ===== MaterialUpdateRequest (تحديث المادة) =====
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MaterialUpdateRequest {

            @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
            private String title;

            @Pattern(regexp = "^(http|https)://.*$", message = "PDF URL must be a valid URL")
            private String pdfUrl;

            @Pattern(regexp = "^(http|https)://.*$", message = "Video URL must be a valid URL")
            private String videoUrl;
        }
    }

