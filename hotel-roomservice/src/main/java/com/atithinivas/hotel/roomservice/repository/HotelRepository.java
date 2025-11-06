package com.atithinivas.hotel.roomservice.repository;

import com.atithinivas.hotel.roomservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel ,Long> {

    // Required for the search function
    List<Hotel> findByLocation(String location);

    // Optional: find hotels by rating
    List<Hotel> findByRating(String rating);

    // Required for Room creation check
    boolean existsByHotelId(String hotelId);
    
    @Query("SELECT h FROM Hotel h WHERE h.hotelId = :hotelId")
    Optional<Hotel> findHotelByHotelId(@Param("hotelId") String hotelId);

}