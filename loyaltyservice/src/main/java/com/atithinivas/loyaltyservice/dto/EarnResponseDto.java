package com.atithinivas.loyaltyservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EarnResponseDto {
    private boolean success;
    private String message;
    private Long newBalance;
    private String txnCode;
}