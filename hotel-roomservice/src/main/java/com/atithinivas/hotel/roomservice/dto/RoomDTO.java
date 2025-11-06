package com.atithinivas.hotel.roomservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private String roomId;
    private String hotelId;
    private Boolean availability;
    private String type;
    private Double price;
    private List<String> features;
    private String managerName;  // Maps to createdBy/updatedBy
}