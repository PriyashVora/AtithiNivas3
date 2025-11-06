//package com.project.cts.atinv.entity;
////Room (RoomID, HotelID, Type, Price, Availability, Features)
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//
//@Entity
//public class Room {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int roomID;
//    private String type;
//    private String price;
//    private boolean availability;
//    private String features;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    private int roomNo;
//    @ManyToOne
//    @JoinColumn (
//            name = "hotel_id",
//            referencedColumnName = "hotelId"
//    )
//    private Hotel hotel;
//    @Version
//    @NotNull
//    private Long version=1L;
//
//    public int getRoomNo() {
//        return roomNo;
//    }
//
//    public void setRoomNo(int roomNo) {
//        this.roomNo = roomNo;
//    }
//
//
//
//    public int getRoomID() {
//        return roomID;
//    }
//
//    public void setRoomID(int roomID) {
//        this.roomID = roomID;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public boolean isAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(boolean availability) {
//        this.availability = availability;
//    }
//
//    public String getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(String features) {
//        this.features = features;
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
//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
//
//}
