//package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import lombok.Data;
//import java.time.LocalDate;
//
//
//@Data
//public class BookingRequestDTO {
//
//    private String userId;
//    private String hotelId;
//    private String roomId;
//
//    private LocalDate checkInDate;
//    private LocalDate checkOutDate;
//
//}

package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class BookingRequestDTO {

//    @NotNull(message = "User ID is required")
//    private Integer userId;

    @NotNull(message = "Hotel ID is required")
    private String hotelId;

    @NotNull(message = "Room ID is required")
    private Integer roomId;

    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date must be in the present or future")
    private LocalDateTime checkInDate;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private LocalDateTime checkOutDate;

}