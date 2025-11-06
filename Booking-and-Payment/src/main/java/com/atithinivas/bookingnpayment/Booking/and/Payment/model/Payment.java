//
//package com.atithinivas.bookingnpayment.Booking.and.Payment.model;
//
//import jakarta.persistence.*;
//        import lombok.Data;
//import java.util.UUID;
//
//@Data
//@Entity
//@Table(name = "payments")
//public class Payment {
//
//    @Id
//    @Column(length = 50)
//    private String paymentId;
//
//    @Column(nullable = false, length = 50)
//    private String userId;
//
//    @Column(nullable = false, length = 50)
//    private String bookingid;
//
//    @Column(nullable = false)
//    private Double amount;
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 20)
//    private Status status;
//
//    @Column(length = 50)
//    private String paymentMethod;
//
//    // Enum for Status
//    public enum Status {
//        PENDING,
//        COMPLETED,
//        FAILED,
//        REFUNDED
//    }
//
//    @PrePersist
//    public void generateId() {
//        if (this.paymentId == null) {
//
//            this.paymentId = "P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
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
@Table(name = "payments")
@EntityListeners(AuditingEntityListener.class) // For @CreatedDate and @LastModifiedDate
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "booking_id", nullable = false)
    private Integer bookingId;

    @Column(nullable = false)
    private Integer amount; // As per ER diagram 'amount INT'

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

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


    // Enum for Status
    public enum Status {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = Status.PENDING;
        }
    }
}



