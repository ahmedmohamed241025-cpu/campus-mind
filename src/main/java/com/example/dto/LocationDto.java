package com.example.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LocationDto {



    // <<<<<<<<<<>>>>>>>>>> LocationRequest (إنشاء مكان) <<<<<<<<<<<>>>>>>>>>>>
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationRequest {

        @NotBlank(message = "Location name cannot be blank")
        @Size(min = 3, max = 100, message = "Location name must be between 3 and 100 characters")
        private String name;

        @NotNull(message = "Latitude is required")
        @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
        private Double latitude;

        @NotNull(message = "Longitude is required")
        @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
        private Double longitude;
    }

    // ===== LocationResponse (الرد للطالب) =====
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationResponse {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
    }

    // <<<<<<<<<<<<<<< LocationUpdateRequest (تحديث المكان) >>>>>>>>>>>>>>>>
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationUpdateRequest {

        @Size(min = 3, max = 100, message = "Location name must be between 3 and 100 characters")
        private String name;

        @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
        private Double latitude;

        @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
        private Double longitude;
    }
}



