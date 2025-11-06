//package com.project.cts.atinv.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//
////Review (ReviewID, UserID, HotelID, Rating, Comment, Timestamp)
//@Data
//@Entity
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int reviewId;
//    private String rating;
//    private String comment;
//    private LocalDateTime timestamp;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    private String managerResponse;
//    private LocalDateTime responseDate;
//    @Version
//    @NotNull
//    private Long version=1L;
//
//    @OneToOne
//    @JoinColumn(
//            name ="booking_id",
//            referencedColumnName = "bookingId"
//    )
//    private Booking booking;
//    @ManyToOne
//    @JoinColumn(
//            name="user_id",
//            referencedColumnName = "guestId"
//    )
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(
//            name="hotel_id",
//            referencedColumnName = "hotelId"
//    )
//    private Hotel hotel;
//
//    public String getManagerResponse() {
//        return managerResponse;
//    }
//
//    public void setManagerResponse(String managerResponse) {
//        this.managerResponse = managerResponse;
//    }
//
//    public LocalDateTime getResponseDate() {
//        return responseDate;
//    }
//
//    public void setResponseDate(LocalDateTime responseDate) {
//        this.responseDate = responseDate;
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
//
//    public int getReviewId() {
//        return reviewId;
//    }
//
//    public void setReviewId(int reviewId) {
//        this.reviewId = reviewId;
//    }
//
//    public String getRating() {
//        return rating;
//    }
//
//    public void setRating(String rating) {
//        this.rating = rating;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
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
//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
//
//
//}
