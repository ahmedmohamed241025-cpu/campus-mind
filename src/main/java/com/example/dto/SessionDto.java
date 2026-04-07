package com.example.dto;


import com.example.model.SessionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private String id;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @NotNull(message = "Location ID is required")
        private Long locationId;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @NotNull(message = "Instructor ID is required")
        private Long instructorId;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @NotNull(message = "Course ID is required")
        private Long courseId;

        //<<<<<<<<<Validation>>>>>>>>>>>>>>
        @NotNull(message = "Session type is required")
        private SessionType type;  // Lecture, Lab, Exam...

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        private LocalDateTime startTime;

        @NotNull(message = "End time is required")
        @Future(message = "End time must be in the future")
        private LocalDateTime endTime;

        @NotBlank(message = "Session name cannot be blank")
        @Size(min = 3, max = 100, message = "Session name must be between 3 and 100 characters")
        private String name;

        private String courseName;
        private String instructorName;
        private String locationName;

}




