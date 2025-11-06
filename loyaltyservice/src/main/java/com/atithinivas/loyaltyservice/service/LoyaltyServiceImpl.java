package com.atithinivas.loyaltyservice.service;

import com.atithinivas.loyaltyservice.dto.*;
import com.atithinivas.loyaltyservice.entity.LoyaltyAccountEntity;
import com.atithinivas.loyaltyservice.entity.PointsTxnEntity;
import com.atithinivas.loyaltyservice.repository.LoyaltyAccountRepository;
import com.atithinivas.loyaltyservice.repository.PointsTxnRepository;
import com.atithinivas.loyaltyservice.util.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    private final LoyaltyAccountRepository accountRepo;
    private final PointsTxnRepository txnRepo;

    // Simple in-memory config for now (persist later if needed)
    private volatile Long pointsPerBooking = 50L;

    public LoyaltyServiceImpl(LoyaltyAccountRepository accountRepo, PointsTxnRepository txnRepo) {
        this.accountRepo = accountRepo;
        this.txnRepo = txnRepo;
    }

    // ========== helpers ==========
    private AccountResponseDto toAccountDto(LoyaltyAccountEntity a) {
        return AccountResponseDto.builder()
                .userId(a.getUserId())
                .pointsBalance(a.getPointsBalance())
                .lastUpdated(a.getLastUpdated())
                .build();
    }

    private TxnResponseDto toTxnDto(PointsTxnEntity t) {
        return TxnResponseDto.builder()
                .txnCode(t.getTxnCode())
                .txnType(t.getTxnType())
                .points(t.getPoints())
                .bookingId(t.getBookingId())
                .note(t.getNote())
                .txnTime(t.getTxnTime())
                .build();
    }

    private LoyaltyAccountEntity getOrCreate(Long userId) {
        return accountRepo.findByUserId(userId)
                .orElseGet(() -> accountRepo.save(
                        LoyaltyAccountEntity.builder()
                                .userId(userId)
                                .pointsBalance(0L)
                                .build()
                ));
    }

    // ========== accounts ==========
    @Override
    public AccountResponseDto getAccount(Long userId) {
        return toAccountDto(getOrCreate(userId));
    }

    @Override
    public BalanceResponseDto getBalance(Long userId) {
        var acc = getOrCreate(userId);
        return BalanceResponseDto.builder()
                .userId(userId)
                .balance(acc.getPointsBalance())
                .build();
    }

    @Override
    public List<TxnResponseDto> getHistory(Long userId) {
        return txnRepo.findByUserIdOrderByTxnTimeDesc(userId)
                .stream().map(this::toTxnDto).collect(Collectors.toList());
    }

    // ========== actions ==========
    @Override
    @Transactional
    public RedeemResponseDto redeem(RedeemRequestDto req) {
        var acc = getOrCreate(req.getUserId());
        long toRedeem = Math.max(0, req.getPoints() == null ? 0 : req.getPoints());
        if (toRedeem <= 0) {
            return RedeemResponseDto.builder().success(false).message("Invalid points to redeem").newBalance(acc.getPointsBalance()).build();
        }
        if (acc.getPointsBalance() < toRedeem) {
            return RedeemResponseDto.builder().success(false).message("Insufficient balance").newBalance(acc.getPointsBalance()).build();
        }

        acc.setPointsBalance(acc.getPointsBalance() - toRedeem);
        accountRepo.save(acc);

        var txn = PointsTxnEntity.builder()
                .txnCode(IdUtil.txnCode())
                .userId(req.getUserId())
                .bookingId(req.getBookingId())
                .txnType("REDEEM")
                .points(toRedeem)
                .note("Redeemed for booking " + (req.getBookingId() == null ? "" : req.getBookingId()))
                .txnTime(LocalDateTime.now())
                .build();
        txnRepo.save(txn);

        return RedeemResponseDto.builder()
                .success(true)
                .message("Redeemed " + toRedeem + " points")
                .newBalance(acc.getPointsBalance())
                .txnCode(txn.getTxnCode())
                .build();
    }

    @Override
    @Transactional
    public EarnResponseDto earn(EarnRequestDto req) {
        var acc = getOrCreate(req.getUserId());
        long toEarn = (req.getPoints() == null || req.getPoints() <= 0) ? pointsPerBooking : req.getPoints();

        acc.setPointsBalance(acc.getPointsBalance() + toEarn);
        accountRepo.save(acc);

        var txn = PointsTxnEntity.builder()
                .txnCode(IdUtil.txnCode())
                .userId(req.getUserId())
                .bookingId(req.getBookingId())
                .txnType("EARN")
                .points(toEarn)
                .note("Earned from booking " + (req.getBookingId() == null ? "" : req.getBookingId()))
                .txnTime(LocalDateTime.now())
                .build();
        txnRepo.save(txn);

        return EarnResponseDto.builder()
                .success(true)
                .message("Earned " + toEarn + " points")
                .newBalance(acc.getPointsBalance())
                .txnCode(txn.getTxnCode())
                .build();
    }

    // ========== config ==========
    @Override
    public ConfigDto getConfig() {
        return ConfigDto.builder().pointsPerBooking(pointsPerBooking).build();
    }

    @Override
    public ConfigDto updateConfig(ConfigDto config) {
        if (config.getPointsPerBooking() != null && config.getPointsPerBooking() >= 0) {
            pointsPerBooking = config.getPointsPerBooking();
        }
        return getConfig();
    }
}