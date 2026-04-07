package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


import com.example.model.Status;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

    public class AttendanceDto {


    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Student ID is required")
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private Long studentId;

    @NotNull(message = "Student ID is required")
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private Long sessionId;

    @NotNull(message = "Status is required")
    private Status status;

            private LocalDateTime timestamp;
            private String studentName;
            private String sessionName;
        }


