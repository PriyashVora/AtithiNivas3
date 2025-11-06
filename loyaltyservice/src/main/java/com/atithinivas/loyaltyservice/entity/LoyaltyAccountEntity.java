package com.atithinivas.loyaltyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loyalty_account")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LoyaltyAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "points_balance", nullable = false)
    private Long pointsBalance;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        if (pointsBalance == null) pointsBalance = 0L;
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}