//package com.atithinivas.bookingnpayment.Booking.and.Payment.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Data
//@Entity
//@Table(name = "bookings")
//public class Booking {
//
//    @Id
//    @Column(length = 50)
//    private String bookingId;
//
//    @Column(nullable = false, length = 50)
//    private String userId;
//
//    @Column(nullable = false, length = 50)
//    private String hotelId;
//
//    @Column(nullable = false, length = 50)
//    private String roomId;
//
//    @Column(nullable = false)
//    private LocalDate checkInDate;
//
//    @Column(nullable = false)
//    private LocalDate checkOutDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 20)
//    private Status status;
//
//    @Column(length = 50)
//    private String paymentId;
//
//    public enum Status {
//        PENDING,
//        CONFIRMED,
//        CANCELLED,
//        CHECKED_IN,
//        COMPLETED
//    }
//
//    @PrePersist
//    public void generateId() {
//        if (this.bookingId == null) {
//            // Generates a random ID like "B" + 8 hex characters
//            this.bookingId = "B" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//        }
//        if (this.status == null) {
//            this.status = Status.PENDING;
//        }
//    }
//}



package com.atithinivas.bookingnpayment.Booking.and.Payment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
@EntityListeners(AuditingEntityListener.class) // For @CreatedDate and @LastModifiedDate
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "hotel_id", nullable = false)
    private String hotelId;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "check_in_date", nullable = false)
    private LocalDateTime checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDateTime checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @Column(name = "payment_id")
    private Integer paymentId;

    // Auditing fields from ER diagram
    @CreatedDate
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @CreatedBy // <-- ADD THIS
    @Column(name = "created_by", length = 255, nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy // <-- ADD THIS
    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Version
    @Column(name = "version")
    private Long version;

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED,
        CHECKED_IN,
        COMPLETED
    }

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = Status.PENDING;
        }
    }
}



