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
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Internal primary key

    @Column(name = "hotel_id", unique = true, nullable = false)
    private String hotelId; // UUID-style ID for frontend

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String location;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> amenities;

    // CORRECTED: Validation for a single digit 0, 1, 2, 3, 4, or 5
    @Pattern(regexp = "^[0-5]$", message = "Rating must be between 0 and 5")
    private String rating;

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