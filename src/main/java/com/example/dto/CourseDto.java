package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

public class CourseDto {

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CourseRequest {
            //>>>>>>>>>>>>>>>>>>>>>>Validation (التحقق)>>>>>>>>>>>>>>>>>>>>>>
            @NotBlank(message = "Course name cannot be blank")
            @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
            private String name;
            @NotBlank(message = "Course code cannot be blank")
            @Size(min = 3, max = 20, message = "Course code must be between 3 and 20 characters")
            @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Course code must contain only letters and numbers")
            private String code;

        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CourseResponse {
            private Long id;
            private String name;
            private String code;
        }


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CourseUpdateRequest {
            @NotBlank(message = "Course name cannot be blank")
            @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
            private String name;

        }


    }

