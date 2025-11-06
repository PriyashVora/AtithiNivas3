package com.atithinivas.hotel.roomservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal primary key

    @Column(name = "room_id", unique = true, nullable = false)
    private String roomId; // UUID-style ID for frontend

    @NotBlank
    @Size(max = 255)
    private String hotelId; // Public UUID of the hotel this room belongs to

    @NotNull
    private Boolean availability;

    @NotBlank
    @Size(max = 50)
    private String type; // e.g., Single, Double, Suite

    @NotNull
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> features; // e.g., Balcony, Sea View

    @NotBlank
    private String createdBy;

    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime updatedOn;

    @Version
    @JsonIgnore
    private Long version;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}