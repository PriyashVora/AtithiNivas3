package com.atithinivas.hotel.roomservice.controller;

import com.atithinivas.hotel.roomservice.dto.RoomDTO;
import com.atithinivas.hotel.roomservice.entity.Room;
import com.atithinivas.hotel.roomservice.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// CHANGED: Set a higher-level base for the API version
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // ✅ Create Room
    // NEW PATH: POST /api/v1/hotels/{hotelId}/rooms
    @PostMapping("/hotels/{hotelId}/rooms")
    public ResponseEntity<RoomDTO> createRoom(@PathVariable String hotelId, @Valid @RequestBody RoomDTO room) {
        // CHANGE: Returns 201 CREATED status
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(hotelId, room));
    }

    // ✅ Get All Rooms (Adapted to nested path: /api/v1/hotels/{hotelId}/rooms)
    @GetMapping("/hotels/{hotelId}/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(@PathVariable String hotelId) {
        // Renamed from getAllRooms in the context of a hotel resource
        return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
    }

    // NOTE: The following methods are kept for general CRUD but assume the user still needs them, even if the path structure is less RESTful.

    // ✅ Get Room by Internal ID (Keeping previous functional structure)
    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    // ✅ Update Room (Keeping previous functional structure)
    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    // ✅ Delete Room (Keeping previous functional structure)
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    // ✅ Get Rooms by Availability (Keeping previous functional structure)
    @GetMapping("/rooms/availability/{availability}")
    public ResponseEntity<List<RoomDTO>> getRoomsByAvailability(@PathVariable Boolean availability) {
        return ResponseEntity.ok(roomService.getRoomsByAvailability(availability));
    }
}