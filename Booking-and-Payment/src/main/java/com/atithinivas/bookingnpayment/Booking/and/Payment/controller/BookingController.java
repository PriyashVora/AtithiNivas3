//
//package com.atithinivas.bookingnpayment.Booking.and.Payment.controller;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.service.BookingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/bookings")
//@CrossOrigin(origins = "*")
//public class BookingController {
//
//    @Autowired
//    private BookingService bookingService;
//
//    @GetMapping
//    public List<BookingResponseDTO> getAllBookings() {
//        return bookingService.getAllBookings();
//    }
//
//    @GetMapping("/{bookingId}")
//    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable String bookingId) {
//        return bookingService.getBookingById(bookingId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable String userId) {
//        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
//    }
//
//    @GetMapping("/room/{roomId}")
//    public ResponseEntity<List<BookingResponseDTO>> getBookingsByRoomId(@PathVariable String roomId) {
//        return ResponseEntity.ok(bookingService.getBookingsByRoomId(roomId));
//    }
//
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<BookingResponseDTO>> getBookingsByHotelId(@PathVariable String hotelId) {
//        return ResponseEntity.ok(bookingService.getBookingsByHotelId(hotelId));
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
//        try {
//            BookingResponseDTO savedBooking = bookingService.createBooking(request);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating booking: " + e.getMessage());
//        }
//    }
//
//    @PatchMapping("/{bookingId}")
//    public ResponseEntity<?> updateBooking(@PathVariable String bookingId, @RequestBody Map<String, Object> updates) {
//        try {
//            Booking.Status status = updates.containsKey("status") ? Booking.Status.valueOf(((String) updates.get("status")).toUpperCase()) : null;
//            String paymentId = updates.containsKey("payment_id") ? (String) updates.get("payment_id") : null;
//
//            BookingResponseDTO updatedBooking = bookingService.updateBooking(bookingId, status, paymentId);
//            return ResponseEntity.ok(updatedBooking);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("Invalid status value provided.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{bookingId}")
//    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
//        try {
//            bookingService.deleteBooking(bookingId);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}






package com.atithinivas.bookingnpayment.Booking.and.Payment.controller;

import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingRequestDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.BookingResponseDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
import com.atithinivas.bookingnpayment.Booking.and.Payment.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingResponseDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Integer bookingId) {
        return bookingService.getBookingById(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByRoomId(@PathVariable Integer roomId) {
        return ResponseEntity.ok(bookingService.getBookingsByRoomId(roomId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(bookingService.getBookingsByHotelId(hotelId));
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequestDTO request,@RequestHeader("X-Role") String role,
            @RequestHeader("X-User-Id") int id) {
        try {
            BookingResponseDTO savedBooking = bookingService.createBooking(request,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating booking: " + e.getMessage());
        }
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer bookingId, @RequestBody Map<String, Object> updates) {
        try {
            Booking.Status status = updates.containsKey("status") ? Booking.Status.valueOf(((String) updates.get("status")).toUpperCase()) : null;
            Integer paymentId = updates.containsKey("payment_id") ? (Integer) updates.get("payment_id") : null;

            BookingResponseDTO updatedBooking = bookingService.updateBooking(bookingId, status, paymentId);
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status or payment_id value provided.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles validation exceptions from @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}