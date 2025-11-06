package com.atithinivas.loyaltyservice.repository;

import com.atithinivas.loyaltyservice.entity.PointsTxnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointsTxnRepository extends JpaRepository<PointsTxnEntity, Long> {
    Optional<PointsTxnEntity> findByTxnCode(String txnCode);
    List<PointsTxnEntity> findByUserIdOrderByTxnTimeDesc(Long userId);
}