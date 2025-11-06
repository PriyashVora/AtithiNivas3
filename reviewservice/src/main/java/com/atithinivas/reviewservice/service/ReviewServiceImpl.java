package com.atithinivas.reviewservice.service;

import com.atithinivas.reviewservice.dto.*;
import com.atithinivas.reviewservice.entity.ReviewEntity;
import com.atithinivas.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repo;

    public ReviewServiceImpl(ReviewRepository repo) {
        this.repo = repo;
    }

    // Convert Entity -> DTO for response
    private ReviewResponseDto toDto(ReviewEntity e) {
        return ReviewResponseDto.builder()
                .reviewId(e.getId())
                .hotelId(e.getHotelId())
                .userId(e.getUserId())
                .comment(e.getComment())
                .rating(e.getRating())
                .timestamp(e.getTimestamp())
                .createdOn(e.getCreatedOn())
                .updatedOn(e.getUpdatedOn())
                .managerResponse(e.getManagerResponse())
                .responseDate(e.getResponseDate())
                .build();
    }

    // Apply fields during creation
    private void applyCreateFields(ReviewEntity e, ReviewRequestDto dto) {
        e.setHotelId(dto.getHotelId());
        e.setUserId(dto.getUserId());
        e.setComment(dto.getComment());
        e.setRating(dto.getRating());
        e.setTimestamp(LocalDateTime.now());
    }

    // Get all reviews
    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    // Get review by ID
    @Override
    public ReviewResponseDto getReviewById(Long reviewId) {
        ReviewEntity e = repo.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found: " + reviewId));
        return toDto(e);
    }

    // Get reviews by user
    @Override
    public List<ReviewResponseDto> getReviewsByUser(Long userId) {
        return repo.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    // Get reviews by hotel
    @Override
    public List<ReviewResponseDto> getReviewsByHotel(Long hotelId) {
        return repo.findByHotelId(hotelId).stream().map(this::toDto).collect(Collectors.toList());
    }

    // Create a new review
    @Override
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        ReviewEntity e = ReviewEntity.builder().build();
        applyCreateFields(e, requestDto);
        ReviewEntity saved = repo.save(e);
        return toDto(saved);
    }

    // Update guest's comment or rating (partial update)
// ReviewServiceImpl.java
    @Override
    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewUpdateDto updateDto) {
        ReviewEntity existing = repo.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found: " + reviewId));

        if (updateDto.getComment() != null) {
            existing.setComment(updateDto.getComment());
        }
        if (updateDto.getRating() != null) {
            existing.setRating(updateDto.getRating()); // already validated 1..5 by @Pattern
        }
        ReviewEntity saved = repo.save(existing);
        return toDto(saved);
    }

    // Manager adds or updates response
    @Override
    @Transactional
    public ReviewResponseDto updateManagerResponse(Long reviewId, ManagerResponseDto responseDto) {
        ReviewEntity existing = repo.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found: " + reviewId));
        existing.setManagerResponse(responseDto.getManagerResponse());
        existing.setResponseDate(LocalDateTime.now());
        ReviewEntity saved = repo.save(existing);
        return toDto(saved);
    }

    // Delete review by ID
    @Override
    public void deleteReview(Long reviewId) {
        if (!repo.existsById(reviewId)) {
            throw new ReviewNotFoundException("Review not found: " + reviewId);
        }
        repo.deleteById(reviewId);
    }
}