//package com.project.cts.atinv.entity;
////LoyaltyAccount (LoyaltyID, UserID, PointsBalance, LastUpdated)
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//@Data
//@Entity
//public class LoyaltyAccount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int loyaltyId;
//    private int pointsBalance;
//    private LocalDateTime lastUpdated;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    @Version
//    @NotNull
//    private Long version=1L;
//    @ManyToOne
//    @JoinColumn(
//            name="user_id",
//            referencedColumnName = "guestId"
//    )
//    private User user;
//
//    public int getLoyaltyId() {
//        return loyaltyId;
//    }
//
//    public void setLoyaltyId(int loyaltyId) {
//        this.loyaltyId = loyaltyId;
//    }
//
//    public int getPointsBalance() {
//        return pointsBalance;
//    }
//
//    public void setPointsBalance(int pointsBalance) {
//        this.pointsBalance = pointsBalance;
//    }
//
//    public LocalDateTime getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public void setLastUpdated(LocalDateTime lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(LocalDateTime createdOn) {
//        this.createdOn = createdOn;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public LocalDateTime getUpdatedOn() {
//        return updatedOn;
//    }
//
//    public void setUpdatedOn(LocalDateTime updatedOn) {
//        this.updatedOn = updatedOn;
//    }
//
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//
//}
