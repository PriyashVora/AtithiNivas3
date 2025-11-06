package com.atithinivas.loyaltyservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EarnRequestDto {
    private Long userId;
    private String bookingId;
    private Long points; // if null, service uses the configured per-booking value
}