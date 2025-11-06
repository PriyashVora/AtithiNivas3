package com.atithinivas.reviewservice.controller;

import com.atithinivas.reviewservice.dto.*;
import com.atithinivas.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
API endpoints:
GET    /api/v1/reviews
GET    /api/v1/reviews/{reviewId}
GET    /api/v1/reviews/user/{userId}
GET    /api/v1/reviews/hotel/{hotelId}
POST   /api/v1/reviews
PATCH  /api/v1/reviews/{reviewId}            -> guest partial update (comment/rating)
PATCH  /api/v1/reviews/{reviewId}/response   -> manager response (managerResponse + responseDate)
DELETE /api/v1/reviews/{reviewId}
*/

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET /api/v1/reviews
    // → Fetch all reviews (admin/general listing)
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // GET /api/v1/reviews/{reviewId}
    // → Fetch a specific review by its ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }


    // GET /api/v1/reviews/user/{userId}
    // → Fetch all reviews written by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    // GET /api/v1/reviews/hotel/{hotelId}
    // → Fetch all reviews for a specific hotel
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(reviewService.getReviewsByHotel(hotelId));
    }

    // POST /api/v1/reviews
    // → Create a new review (by guest)
    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto created = reviewService.createReview(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PATCH /api/v1/reviews/{reviewId}
    // → Update review (guest can edit only comment/rating)
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewUpdateDto updateDto) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, updateDto));
    }

    // PATCH /api/v1/reviews/{reviewId}/response
    // → Add or update manager response for a review
    @PatchMapping("/{reviewId}/response")
    public ResponseEntity<ReviewResponseDto> updateManagerResponse(
            @PathVariable Long reviewId,
            @Valid @RequestBody ManagerResponseDto responseDto
    ) {
        return ResponseEntity.ok(reviewService.updateManagerResponse(reviewId, responseDto));
    }

    // DELETE /api/v1/reviews/{reviewId}
    // → Delete a review (admin or internal use)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}