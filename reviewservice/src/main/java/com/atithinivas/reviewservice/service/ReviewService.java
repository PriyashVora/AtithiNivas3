package com.atithinivas.reviewservice.service;

import com.atithinivas.reviewservice.dto.*;
import java.util.List;

/**
 * ReviewService defines all business operations related to guest reviews and manager responses.
 *
 * It supports:
 *  - CRUD operations for guest reviews.
 *  - Manager response management.
 *  - Retrieval of reviews by hotel or user.
 */
public interface ReviewService {

    // ---------------------------------------------------------------------
    // Retrieve all reviews (admin/general use)
    // ---------------------------------------------------------------------
    /**
     * Fetches all reviews from the database.
     *
     * @return List of all reviews as ReviewResponseDto.
     */
    List<ReviewResponseDto> getAllReviews();

    // ---------------------------------------------------------------------
    // Retrieve a specific review by ID
    // ---------------------------------------------------------------------
    /**
     * Fetch a single review by its unique review ID.
     *
     * @param reviewId ID of the review to retrieve.
     * @return ReviewResponseDto containing review details.
     * @throws ReviewNotFoundException if the review is not found.
     */
    ReviewResponseDto getReviewById(Long reviewId);

    // ---------------------------------------------------------------------
    // Retrieve reviews written by a specific user
    // ---------------------------------------------------------------------
    /**
     * Fetch all reviews posted by a given user.
     *
     * @param userId User ID for which reviews should be fetched.
     * @return List of ReviewResponseDto for the given user.
     */
    List<ReviewResponseDto> getReviewsByUser(Long userId);

    // ---------------------------------------------------------------------
    // Retrieve reviews for a specific hotel
    // ---------------------------------------------------------------------
    /**
     * Fetch all reviews for a particular hotel.
     *
     * @param hotelId Hotel ID for which reviews should be fetched.
     * @return List of ReviewResponseDto for that hotel.
     */
    List<ReviewResponseDto> getReviewsByHotel(Long hotelId);

    // ---------------------------------------------------------------------
    // Create a new review
    // ---------------------------------------------------------------------
    /**
     * Creates a new guest review entry.
     *
     * @param requestDto Data transfer object containing guest-provided comment and rating.
     * @return ReviewResponseDto representing the newly created review.
     */
    ReviewResponseDto createReview(ReviewRequestDto requestDto);

    // ---------------------------------------------------------------------
    // Update existing review (guest partial update)
    // ---------------------------------------------------------------------
    /**
     * Updates an existing review (guest-initiated partial update).
     *
     * Guests can only modify their own comment and rating — hotelId/userId remain fixed.
     *
     * @param reviewId ID of the review to update.
     * @param updateDto DTO containing fields to update (comment, rating).
     * @return Updated review as ReviewResponseDto.
     * @throws ReviewNotFoundException if the review ID does not exist.
     */
    // ReviewService.java
    ReviewResponseDto updateReview(Long reviewId, ReviewUpdateDto updateDto);;

    // ---------------------------------------------------------------------
    // Add or update manager's response
    // ---------------------------------------------------------------------
    /**
     * Allows the hotel manager to respond to a guest’s review.
     *
     * @param reviewId ID of the review being responded to.
     * @param responseDto DTO containing the manager's response text.
     * @return Updated review with manager response.
     * @throws ReviewNotFoundException if the review ID does not exist.
     */
    ReviewResponseDto updateManagerResponse(Long reviewId, ManagerResponseDto responseDto);

    // ---------------------------------------------------------------------
    // Delete a review
    // ---------------------------------------------------------------------
    /**
     * Deletes an existing review from the system.
     *
     * @param reviewId ID of the review to delete.
     * @throws ReviewNotFoundException if the review does not exist.
     */
    void deleteReview(Long reviewId);
}