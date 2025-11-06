package com.atithinivas.hotel.roomservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchRequestDTO {

    @NotBlank(message = "City parameter (location) is required for search.")
    private String city;

    @NotNull(message = "Check-in date is required.")
    @FutureOrPresent(message = "Check-in date cannot be in the past.")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required.")
    private LocalDate checkOut;

    // Custom Validation: Check-out date must be after Check-in date.
    @AssertTrue(message = "Check-out date must be after Check-in date.")
    private boolean isDatesValid() {
        if (checkIn == null || checkOut == null) {
            return false; // Validation is handled by @NotNull
        }
        return checkOut.isAfter(checkIn);
    }
}