package com.atithinivas.hotel.roomservice.dto;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private String hotelId;      // UUID-style ID
    private String name;
    private String location;
    private List<String> amenities;
    private String rating;
    private String managerName;  // Derived from User entity
}