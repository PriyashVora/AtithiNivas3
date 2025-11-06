//package com.project.cts.atinv.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//
////Payment (PaymentID, UserID, BookingID, Amount, Status, PaymentMethod)
//@Data
//@Entity
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int paymentId;
//    private int Amount;
//    @Enumerated(EnumType.STRING) // Stores enum as string in DB (recommended)
//    private Status status;
//
//    public enum Status {
//        PENDING,
//        COMPLETED
//    }
//    private String paymentMethod;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    @Version
//    @NotNull
//    private Long version=1L;
//
//    public int getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(int paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public int getAmount() {
//        return Amount;
//    }
//
//    public void setAmount(int amount) {
//        Amount = amount;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
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
//    public Booking getBooking() {
//        return booking;
//    }
//
//    public void setBooking(Booking booking) {
//        this.booking = booking;
//    }
//
//    @ManyToOne
//    @JoinColumn (
//            name="user_id",
//            referencedColumnName = "guestId"
//    )
//    private User user;
//    @OneToOne
//    @JoinColumn(
//            name="booking_id",
//            referencedColumnName = "bookingId"
//    )
//    private Booking booking;
//}
