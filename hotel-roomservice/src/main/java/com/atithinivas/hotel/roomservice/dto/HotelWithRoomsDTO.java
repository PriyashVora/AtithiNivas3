package com.atithinivas.hotel.roomservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelWithRoomsDTO {
    private String hotelId;
    private String name;
    private String location;
    private String rating;
    private List<String> amenities;

    // The list of rooms available for the searched dates
    private List<RoomDTO> availableRooms;
}