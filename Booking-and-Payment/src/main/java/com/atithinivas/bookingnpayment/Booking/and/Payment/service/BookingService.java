//
//
//package com.atithinivas.bookingnpayment.Booking.and.Payment.service;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import java.util.List;
//import java.util.Optional;
//
//public interface BookingService {
//    List<BookingResponseDTO> getAllBookings();
//    Optional<BookingResponseDTO> getBookingById(String bookingId);
//    List<BookingResponseDTO> getBookingsByUserId(String userId);
//    List<BookingResponseDTO> getBookingsByRoomId(String roomId);
//    List<BookingResponseDTO> getBookingsByHotelId(String hotelId);
//    BookingResponseDTO createBooking(BookingRequestDTO bookingRequest) throws Exception;
//    BookingResponseDTO updateBooking(String bookingId, Booking.Status status, String paymentId) throws Exception;
//    void deleteBooking(String bookingId);
//}





package com.atithinivas.bookingnpayment.Booking.and.Payment.service;

import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<BookingResponseDTO> getAllBookings();
    Optional<BookingResponseDTO> getBookingById(Integer bookingId);
    List<BookingResponseDTO> getBookingsByUserId(Integer userId);
    List<BookingResponseDTO> getBookingsByRoomId(Integer roomId);
    List<BookingResponseDTO> getBookingsByHotelId(String hotelId);
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequest,int id) throws Exception;
    BookingResponseDTO updateBooking(Integer bookingId, Booking.Status status, Integer paymentId) throws Exception;
    void deleteBooking(Integer bookingId);
}
