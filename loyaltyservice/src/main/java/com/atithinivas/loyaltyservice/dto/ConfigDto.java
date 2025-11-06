package com.atithinivas.loyaltyservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConfigDto {
    private Long pointsPerBooking; // e.g., 50 (admin-changeable)
}