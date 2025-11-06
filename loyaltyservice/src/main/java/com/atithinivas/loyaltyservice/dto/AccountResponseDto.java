package com.atithinivas.loyaltyservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountResponseDto {
    private Long userId;
    private Long pointsBalance;
    private LocalDateTime lastUpdated;
}