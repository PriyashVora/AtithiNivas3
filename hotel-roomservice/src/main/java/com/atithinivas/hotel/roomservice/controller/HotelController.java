package com.atithinivas.hotel.roomservice.controller;

import com.atithinivas.hotel.roomservice.dto.HotelDTO;
import com.atithinivas.hotel.roomservice.dto.HotelWithRoomsDTO; // NEW IMPORT
import com.atithinivas.hotel.roomservice.dto.SearchRequestDTO; // NEW IMPORT
import com.atithinivas.hotel.roomservice.entity.Hotel;
import com.atithinivas.hotel.roomservice.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // ✅ NEW: Hotel Search Endpoint
    // Handles GET /api/v1/hotels/search?city=...&checkIn=...
    @GetMapping("/search")
    public ResponseEntity<List<HotelWithRoomsDTO>> searchHotels(@Valid @ModelAttribute SearchRequestDTO request) {
    	return ResponseEntity.ok(hotelService.searchHotels(request));

        // Positive Flow: 200 OK
    }

    // ✅ Create Hotel
    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(
            @Valid @RequestBody HotelDTO hotel,
            @RequestHeader("X-Role") String role,
            @RequestHeader("X-User-Id") int id) {

        if ("ADMIN".equals(role) || "HOTELMANAGER".equals(role)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(null); // or you can return a custom error DTO if preferred
    }


    // ✅ Get All Hotels
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    // ✅ Get Hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.getHotelByHotelId(id));
    }	

    // ✅ Update Hotel
    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotel));
    }

    // ✅ Delete Hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }

    // ✅ Get Hotels by Location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<HotelDTO>> getHotelsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(hotelService.getHotelsByLocation(location));
    }

    // ✅ Get Hotels by Rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<HotelDTO>> getHotelsByRating(@PathVariable String rating) {
        return ResponseEntity.ok(hotelService.getHotelsByRating(rating));
    }
}