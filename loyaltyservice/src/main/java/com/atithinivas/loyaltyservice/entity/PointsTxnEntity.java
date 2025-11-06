package com.atithinivas.loyaltyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "points_txn")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PointsTxnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private Long id;

    @Column(name = "txn_code", unique = true, nullable = false, length = 64)
    private String txnCode; // e.g., PTX-2025-XXXX

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "booking_id")
    private String bookingId; // nullable for manual adjustments

    @Column(name = "txn_type", nullable = false, length = 16)
    private String txnType; // EARN / REDEEM / ADJUST

    @Column(name = "points", nullable = false)
    private Long points; // positive for EARN, positive input for REDEEM (we’ll subtract in service)

    @Column(name = "note")
    private String note;

    @Column(name = "txn_time", nullable = false)
    private LocalDateTime txnTime;

    @PrePersist
    public void prePersist() {
        if (txnTime == null) txnTime = LocalDateTime.now();
    }
}