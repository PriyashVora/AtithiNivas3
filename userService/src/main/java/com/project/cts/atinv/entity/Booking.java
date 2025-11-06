//package com.project.cts.atinv.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import org.hibernate.annotations.ColumnDefault;
//
//import javax.security.auth.login.CredentialNotFoundException;
//import java.time.LocalDateTime;
//
////Booking (BookingID, UserID, RoomID, CheckInDate, CheckOutDate, Status, PaymentID)
//@Entity
//public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int bookingId;
//    private LocalDateTime checkInDate;
//    private LocalDateTime checkOutDate;
//    @Enumerated(EnumType.STRING) // Stores enum as string in DB (recommended)
//    private Status status;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    @Version
//    @NotNull
//    private Long version=1L;
//
//    public int getBookingId() {
//        return bookingId;
//    }
//
//    public void setBookingId(int bookingId) {
//        this.bookingId = bookingId;
//    }
//
//    public LocalDateTime getCheckInDate() {
//        return checkInDate;
//    }
//
//    public void setCheckInDate(LocalDateTime checkInDate) {
//        this.checkInDate = checkInDate;
//    }
//
//    public LocalDateTime getCheckOutDate() {
//        return checkOutDate;
//    }
//
//    public void setCheckOutDate(LocalDateTime checkOutDate) {
//        this.checkOutDate = checkOutDate;
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
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Payment getPayment() {
//        return payment;
//    }
//
//    public void setPayment(Payment payment) {
//        this.payment = payment;
//    }
//
//    private enum Status {
//        PENDING,
//        COMPLETED
//    }
//    @ManyToOne
//    @JoinColumn (
//            name = "user_id",
//            referencedColumnName = "guestId"
//    )
//    private User user;
//    @ManyToOne
//    @JoinColumn (
//            name = "room_id",
//            referencedColumnName = "roomId"
//    )
//    private Room room;
//
//    @ManyToOne
//    @JoinColumn (
//            name="payment_id",
//            referencedColumnName = "paymentId"
//    )
//    private Payment payment;
//}
