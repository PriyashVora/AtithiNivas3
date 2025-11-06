package com.atithinivas.reviewservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * DTO for creating or updating a guest review.
 * Sent from frontend when a guest submits or edits a review.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto implements Serializable {

    @NotNull(message = "hotelId is required")
    private Long hotelId;

    @NotNull(message = "userId is required")
    private Long userId;

    @Size(max = 255)
    private String comment;

    // store as string to match DB type; validation kept simple
    @Pattern(regexp = "^[1-5]$", message = "rating must be 1,2,3,4 or 5")
    @Schema(example = "5", description = "Allowed values: 1-5")
    private String rating;
}