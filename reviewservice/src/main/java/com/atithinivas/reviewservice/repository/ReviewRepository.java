package com.atithinivas.reviewservice.repository;

import com.atithinivas.reviewservice.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    // Fetch all reviews written by a specific user
    List<ReviewEntity> findByUserId(Long userId);

    // Fetch all reviews for a specific hotel
    List<ReviewEntity> findByHotelId(Long hotelId);
}