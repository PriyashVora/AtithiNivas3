package com.atithinivas.loyaltyservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TxnResponseDto {
    private String txnCode;
    private String txnType; // EARN/REDEEM/ADJUST
    private Long points;
    private String bookingId;
    private String note;
    private LocalDateTime txnTime;
}