package com.atithinivas.loyaltyservice.repository;

import com.atithinivas.loyaltyservice.entity.LoyaltyAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccountEntity, Long> {
    Optional<LoyaltyAccountEntity> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}