package com.atithinivas.hotel.roomservice.service;

import com.atithinivas.hotel.roomservice.dto.HotelDTO;
import com.atithinivas.hotel.roomservice.dto.HotelWithRoomsDTO;
import com.atithinivas.hotel.roomservice.dto.SearchRequestDTO;
import com.atithinivas.hotel.roomservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    HotelDTO createHotel(HotelDTO response);
    List<HotelDTO> getAllHotels();
    HotelDTO getHotelByHotelId(String id);
    HotelDTO updateHotel(Long id, Hotel hotelDetails);
    void deleteHotel(Long id);

    // Custom queries
    List<HotelDTO> getHotelsByLocation(String location);
    List<HotelDTO> getHotelsByRating(String rating);

    // NEW: Hotel Search API contract
    List<HotelWithRoomsDTO> searchHotels(SearchRequestDTO request);
}