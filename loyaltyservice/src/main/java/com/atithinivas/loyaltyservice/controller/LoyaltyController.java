package com.atithinivas.loyaltyservice.controller;

import com.atithinivas.loyaltyservice.dto.*;
import com.atithinivas.loyaltyservice.service.LoyaltyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {

    private final LoyaltyService svc;
    public LoyaltyController(LoyaltyService svc) { this.svc = svc; }

    // GET /api/v1/loyalty/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(svc.getAccount(userId));
    }

    // GET /api/v1/loyalty/{userId}/balance
    @GetMapping("/{userId}/balance")
    public ResponseEntity<BalanceResponseDto> getBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(svc.getBalance(userId));
    }

    // GET /api/v1/loyalty/{userId}/txns
    @GetMapping("/{userId}/txns")
    public ResponseEntity<List<TxnResponseDto>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(svc.getHistory(userId));
    }

    // POST /api/v1/loyalty/redeem
    @PostMapping("/redeem")
    public ResponseEntity<RedeemResponseDto> redeem(@RequestBody RedeemRequestDto req) {
        return ResponseEntity.ok(svc.redeem(req));
    }

    // POST /api/v1/loyalty/earn
    @PostMapping("/earn")
    public ResponseEntity<EarnResponseDto> earn(@RequestBody EarnRequestDto req) {
        return ResponseEntity.ok(svc.earn(req));
    }

    // GET /api/v1/loyalty/config
    @GetMapping("/config")
    public ResponseEntity<ConfigDto> getConfig() {
        return ResponseEntity.ok(svc.getConfig());
    }

    // PATCH /api/v1/loyalty/config
    @PatchMapping("/config")
    public ResponseEntity<ConfigDto> updateConfig(@RequestBody ConfigDto config) {
        return ResponseEntity.ok(svc.updateConfig(config));
    }
}