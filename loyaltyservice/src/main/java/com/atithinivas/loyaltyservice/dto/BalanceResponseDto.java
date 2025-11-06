package com.atithinivas.loyaltyservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BalanceResponseDto {
    private Long userId;
    private Long balance;
}