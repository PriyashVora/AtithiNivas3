//package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import lombok.Data;
//import java.time.LocalDate;
//
//
//@Data
//public class BookingResponseDTO {
//
//    private String bookingId;
//    private String userId;
//    private String hotelId;
//    private String roomId;
//    private LocalDate checkInDate;
//    private LocalDate checkOutDate;
//    private Booking.Status status;
//    private String paymentId;
//
//    public static BookingResponseDTO fromEntity(Booking booking) {
//        BookingResponseDTO dto = new BookingResponseDTO();
//        dto.setBookingId(booking.getBookingId());
//        dto.setUserId(booking.getUserId());
//        dto.setHotelId(booking.getHotelId());
//        dto.setRoomId(booking.getRoomId());
//        dto.setCheckInDate(booking.getCheckInDate());
//        dto.setCheckOutDate(booking.getCheckOutDate());
//        dto.setStatus(booking.getStatus());
//        dto.setPaymentId(booking.getPaymentId());
//        return dto;
//    }
//}

package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;

import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class BookingResponseDTO {

    private Integer bookingId;
    private Integer userId;
    private String hotelId;
    private Integer roomId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Booking.Status status;
    private Integer paymentId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long version;


    private String createdBy; // <-- ADD THIS
    private String updatedBy; // <-- ADD THIS



    public static BookingResponseDTO fromEntity(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setCreatedBy(booking.getCreatedBy()); // <-- ADD THIS
        dto.setUpdatedBy(booking.getUpdatedBy());
        dto.setUserId(booking.getUserId());
        dto.setHotelId(booking.getHotelId());
        dto.setRoomId(booking.getRoomId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setStatus(booking.getStatus());
        dto.setPaymentId(booking.getPaymentId());
        dto.setCreatedOn(booking.getCreatedOn());
        dto.setUpdatedOn(booking.getUpdatedOn());
        dto.setVersion(booking.getVersion());
        return dto;
    }
}





