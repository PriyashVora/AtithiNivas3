package com.atithinivas.hotel.roomservice.service;

import com.atithinivas.hotel.roomservice.dto.RoomDTO;
import com.atithinivas.hotel.roomservice.entity.Room;

import java.util.List;

public interface RoomService {

    // UPDATED: Accepts hotelId from the URL path for nested resource creation
    RoomDTO createRoom(String hotelId, RoomDTO request);

    List<RoomDTO> getAllRooms();
    RoomDTO getRoomById(Long id);
    RoomDTO updateRoom(Long id, Room roomDetails);
    void deleteRoom(Long id);

    // Custom queries
    List<RoomDTO> getRoomsByHotelId(String hotelId);
    List<RoomDTO> getRoomsByAvailability(Boolean availability);
}