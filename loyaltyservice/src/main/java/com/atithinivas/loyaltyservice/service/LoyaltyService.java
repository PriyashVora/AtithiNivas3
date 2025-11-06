package com.atithinivas.loyaltyservice.service;

import com.atithinivas.loyaltyservice.dto.*;

import java.util.List;

public interface LoyaltyService {

    // Accounts
    AccountResponseDto getAccount(Long userId);
    BalanceResponseDto getBalance(Long userId);

    // Transactions
    List<TxnResponseDto> getHistory(Long userId);

    // Actions
    RedeemResponseDto redeem(RedeemRequestDto request);
    EarnResponseDto earn(EarnRequestDto request);

    // Config
    ConfigDto getConfig();
    ConfigDto updateConfig(ConfigDto config);
}