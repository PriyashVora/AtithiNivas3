package com.atithinivas.reviewservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    // Foreign keys (referencing hotel and user)
    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Guest review fields
    @Column(length = 255)
    private String comment;

    @Column(length = 10)
    private String rating; // Stored as String (matches ERD design)

    // Timestamp when review was created by guest
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Audit metadata
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    // Manager response fields
    @Column(name = "manager_response", length = 1000)
    private String managerResponse;

    @Column(name = "response_date")
    private LocalDateTime responseDate;

    // Version control for optimistic locking
    @Version
    @Column(name = "version")
    private Long version;

    // Auto set timestamps on create
    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
        if (this.timestamp == null) this.timestamp = LocalDateTime.now();
    }

    // Auto update timestamp on modify
    @PreUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}