package com.atithinivas.hotel.roomservice.repository;

import com.atithinivas.hotel.roomservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Custom query to find rooms by the public hotelId
    List<Room> findByHotelId(String hotelId);

    // Custom query to find rooms by availability status
    List<Room> findByAvailability(Boolean availability);
}