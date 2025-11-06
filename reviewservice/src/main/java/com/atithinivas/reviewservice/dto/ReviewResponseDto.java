package com.atithinivas.reviewservice.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO returned in API responses for reviews.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {

    private Long reviewId;
    private Long hotelId;
    private Long userId;
    private String comment;
    private String rating;

    private LocalDateTime timestamp;      // when the review was made
    private LocalDateTime createdOn;      // DB creation time
    private LocalDateTime updatedOn;      // last update time

    private String managerResponse;       // manager’s reply
    private LocalDateTime responseDate;   // when manager responded
}