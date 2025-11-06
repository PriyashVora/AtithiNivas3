//package com.project.cts.atinv.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
////import com.vladmihalcea.hibernate.type.json.JsonType;
//import org.hibernate.annotations.JdbcType;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.annotations.Type;
////import org.hibernate.annotations.TypeDef;
//import org.hibernate.type.SqlTypes;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
////Hotel (HotelID, Name, Location, ManagerID, Amenities, Rating)
//
//@Entity
//public class Hotel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int hotelId;
//    private String name;
//    private String location;
//
//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json")
//    private List<String> amenities;
//
//    private String rating;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//    @Version
//    @NotNull
//    private Long version=1L;
//
//    public int getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(int hotelId) {
//        this.hotelId = hotelId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public List<String> getAmenities() {
//        return amenities;
//    }
//
//    public void setAmenities(List<String> amenities) {
//        this.amenities = amenities;
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
//    @OneToOne
//    @JoinColumn (name = "manager_id",referencedColumnName = "guestId")
//    private User user;
//}
