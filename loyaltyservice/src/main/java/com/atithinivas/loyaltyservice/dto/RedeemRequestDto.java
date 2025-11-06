package com.atithinivas.loyaltyservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RedeemRequestDto {
    private Long userId;
    private Long points;      // to redeem
    private String bookingId; // optional
}