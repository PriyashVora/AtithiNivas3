//package com.atithinivas.bookingnpayment.Booking.and.Payment.service;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.BookingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//
//    @Override
//    public List<BookingResponseDTO> getAllBookings() {
//        return bookingRepository.findAll().stream()
//                .map(BookingResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<BookingResponseDTO> getBookingById(String bookingId) {
//        return bookingRepository.findById(bookingId)
//                .map(BookingResponseDTO::fromEntity);
//    }
//
//    @Override
//    public List<BookingResponseDTO> getBookingsByUserId(String userId) {
//        return bookingRepository.findByUserId(userId).stream()
//                .map(BookingResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BookingResponseDTO> getBookingsByRoomId(String roomId) {
//        return bookingRepository.findByRoomId(roomId).stream()
//                .map(BookingResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BookingResponseDTO> getBookingsByHotelId(String hotelId) {
//        return bookingRepository.findByHotelId(hotelId).stream()
//                .map(BookingResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public BookingResponseDTO createBooking(BookingRequestDTO request) throws Exception {
//        if (request.getUserId() == null || request.getRoomId() == null ||
//                request.getHotelId() == null || request.getCheckInDate() == null ||
//                request.getCheckOutDate() == null) {
//            throw new IllegalArgumentException("User ID, Room ID, Hotel ID, and dates are required.");
//        }
//        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())) {
//            throw new IllegalArgumentException("Check-out date must be after check-in date.");
//        }
//
//
//        Booking newBooking = new Booking();
//        newBooking.setUserId(request.getUserId());
//        newBooking.setRoomId(request.getRoomId());
//        newBooking.setHotelId(request.getHotelId());
//        newBooking.setCheckInDate(request.getCheckInDate());
//        newBooking.setCheckOutDate(request.getCheckOutDate());
//        newBooking.setStatus(Booking.Status.PENDING); // Default status
//
//        Booking savedBooking = bookingRepository.save(newBooking);
//        return BookingResponseDTO.fromEntity(savedBooking);
//    }
//
//    @Override
//    @Transactional
//    public BookingResponseDTO updateBooking(String bookingId, Booking.Status status, String paymentId) throws Exception {
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new Exception("Booking not found with ID: " + bookingId));
//
//        if (status != null) {
//            booking.setStatus(status);
//        }
//        if (paymentId != null) {
//            booking.setPaymentId(paymentId);
//        }
//
//        Booking updatedBooking = bookingRepository.save(booking);
//        return BookingResponseDTO.fromEntity(updatedBooking);
//    }
//
//    @Override
//    @Transactional
//    public void deleteBooking(String bookingId) {
//        if (!bookingRepository.existsById(bookingId)) {
//            throw new RuntimeException("Booking not found with ID: " + bookingId);
//        }
//        bookingRepository.deleteById(bookingId);
//    }
//}





package com.atithinivas.bookingnpayment.Booking.and.Payment.service;

import com.atithinivas.bookingnpayment.Booking.and.Payment.client.HotelClient;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
 
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private HotelClient hotelClient;


    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingResponseDTO> getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .map(BookingResponseDTO::fromEntity);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(Integer userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDTO> getBookingsByRoomId(Integer roomId) {
        return bookingRepository.findByRoomId(roomId).stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDTO> getBookingsByHotelId(String hotelId) {
        return bookingRepository.findByHotelId(hotelId).stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional 
    public BookingResponseDTO createBooking(BookingRequestDTO request,int id) throws Exception {
        // DTO validations handle null checks
        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }
        hotelClient.getHotelById(request.getHotelId()).orElseThrow(
        		()-> new RuntimeException("Hotel Not Found With Id: "+request.getHotelId())
        		); 
        
        

        Booking newBooking = new Booking(); 
        newBooking.setUserId(id);
        newBooking.setRoomId(request.getRoomId());
        newBooking.setHotelId(request.getHotelId());
        newBooking.setCheckInDate(request.getCheckInDate());
        newBooking.setCheckOutDate(request.getCheckOutDate());
        // Status is set to PENDING by @PrePersist

        Booking savedBooking = bookingRepository.save(newBooking);
        return BookingResponseDTO.fromEntity(savedBooking);
    }

    @Override
    @Transactional
    public BookingResponseDTO updateBooking(Integer bookingId, Booking.Status status, Integer paymentId) throws Exception {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new Exception("Booking not found with ID: " + bookingId));

        if (status != null) {
            booking.setStatus(status);
        }
        if (paymentId != null) {
            booking.setPaymentId(paymentId);
        }

        Booking updatedBooking = bookingRepository.save(booking);
        return BookingResponseDTO.fromEntity(updatedBooking);
    }

    @Override
    @Transactional
    public void deleteBooking(Integer bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}