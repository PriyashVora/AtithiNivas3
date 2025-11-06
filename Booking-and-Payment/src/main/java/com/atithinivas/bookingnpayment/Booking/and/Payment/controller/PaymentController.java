//
//package com.atithinivas.bookingnpayment.Booking.and.Payment.controller;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentRequestDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentResponseDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/payment")
//@CrossOrigin(origins = "*")
//public class PaymentController {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @GetMapping
//    public List<PaymentResponseDTO> getAllPayments() {
//        return paymentService.getAllPayments();
//    }
//
//    @GetMapping("/{paymentId}")
//    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable String paymentId) {
//        return paymentService.getPaymentById(paymentId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUserId(@PathVariable String userId) {
//        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
//    }
//
//    @GetMapping("/booking/{bookingId}")
//    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByBookingId(@PathVariable String bookingId) {
//        return ResponseEntity.ok(paymentService.getPaymentsByBookingId(bookingId));
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDTO request) {
//        try {
//            PaymentResponseDTO savedPayment = paymentService.createPayment(request);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment: " + e.getMessage());
//        }
//    }
//
//    @PatchMapping("/{paymentId}")
//    public ResponseEntity<?> updatePaymentStatus(@PathVariable String paymentId, @RequestBody Map<String, Object> updates) {
//        try {
//            Payment.Status status = updates.containsKey("status") ? Payment.Status.valueOf(((String) updates.get("status")).toUpperCase()) : null;
//            PaymentResponseDTO updatedPayment = paymentService.updatePaymentStatus(paymentId, status);
//            return ResponseEntity.ok(updatedPayment);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("Invalid status value provided.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{paymentId}")
//    public ResponseEntity<Void> deletePayment(@PathVariable String paymentId) {
//        try {
//            paymentService.deletePayment(paymentId);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}









package com.atithinivas.bookingnpayment.Booking.and.Payment.controller;

import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentRequestDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentResponseDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
import com.atithinivas.bookingnpayment.Booking.and.Payment.service.PaymentService;
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
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Integer paymentId) {
        return paymentService.getPaymentById(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByBookingId(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBookingId(bookingId));
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequestDTO request) {
        try {
            PaymentResponseDTO savedPayment = paymentService.createPayment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment: " + e.getMessage());
        }
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Integer paymentId, @RequestBody Map<String, Object> updates) {
        try {
            Payment.Status status = updates.containsKey("status") ? Payment.Status.valueOf(((String) updates.get("status")).toUpperCase()) : null;
            PaymentResponseDTO updatedPayment = paymentService.updatePaymentStatus(paymentId, status);
            return ResponseEntity.ok(updatedPayment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value provided.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer paymentId) {
        try {
            paymentService.deletePayment(paymentId);
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