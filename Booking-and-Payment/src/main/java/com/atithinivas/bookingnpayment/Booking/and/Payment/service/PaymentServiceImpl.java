//package com.atithinivas.bookingnpayment.Booking.and.Payment.service;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentRequestDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentResponseDTO;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.BookingRepository;
//import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.PaymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PaymentServiceImpl implements PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Override
//    public List<PaymentResponseDTO> getAllPayments() {
//        return paymentRepository.findAll().stream()
//                .map(PaymentResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<PaymentResponseDTO> getPaymentById(String paymentId) {
//        return paymentRepository.findById(paymentId)
//                .map(PaymentResponseDTO::fromEntity);
//    }
//
//    @Override
//    public List<PaymentResponseDTO> getPaymentsByUserId(String userId) {
//        return paymentRepository.findByUserId(userId).stream()
//                .map(PaymentResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<PaymentResponseDTO> getPaymentsByBookingId(String bookingId) {
//        return paymentRepository.findByBookingid(bookingId).stream()
//                .map(PaymentResponseDTO::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public PaymentResponseDTO createPayment(PaymentRequestDTO request) throws Exception {
//        if (request.getUserId() == null || request.getBookingid() == null ||
//                request.getAmount() == null || request.getPaymentMethod() == null) {
//            throw new IllegalArgumentException("User ID, Booking ID, Amount, and Payment Method are required.");
//        }
//
//        if (!bookingRepository.existsById(request.getBookingid())) {
//            throw new Exception("Cannot create payment for non-existent Booking ID: " + request.getBookingid());
//        }
//
//        Payment newPayment = new Payment();
//        newPayment.setUserId(request.getUserId());
//        newPayment.setBookingid(request.getBookingid());
//        newPayment.setAmount(request.getAmount());
//        newPayment.setPaymentMethod(request.getPaymentMethod());
//        newPayment.setStatus(Payment.Status.COMPLETED); // Default to COMPLETED
//
//        Payment savedPayment = paymentRepository.save(newPayment);
//        return PaymentResponseDTO.fromEntity(savedPayment);
//    }
//
//    @Override
//    @Transactional
//    public PaymentResponseDTO updatePaymentStatus(String paymentId, Payment.Status status) throws Exception {
//        Payment payment = paymentRepository.findById(paymentId)
//                .orElseThrow(() -> new Exception("Payment not found with ID: " + paymentId));
//
//        if (status != null) {
//            payment.setStatus(status);
//        }
//
//        Payment updatedPayment = paymentRepository.save(payment);
//        return PaymentResponseDTO.fromEntity(updatedPayment);
//    }
//
//    @Override
//    @Transactional
//    public void deletePayment(String paymentId) {
//        if (!paymentRepository.existsById(paymentId)) {
//            throw new RuntimeException("Payment not found with ID: " + paymentId);
//        }
//        paymentRepository.deleteById(paymentId);
//    }
//}








package com.atithinivas.bookingnpayment.Booking.and.Payment.service;

import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentRequestDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.dto.PaymentResponseDTO;
import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.BookingRepository;
import com.atithinivas.bookingnpayment.Booking.and.Payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentResponseDTO> getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(PaymentResponseDTO::fromEntity);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(Integer userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(PaymentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByBookingId(Integer bookingId) {
        return paymentRepository.findByBookingId(bookingId).stream() // Updated method name
                .map(PaymentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) throws Exception {
        // DTO validations handle null checks

        if (!bookingRepository.existsById(request.getBookingId())) {
            throw new Exception("Cannot create payment for non-existent Booking ID: " + request.getBookingId());
        }

        // Card validation logic
        if ("CREDIT_CARD".equalsIgnoreCase(request.getPaymentMethod())) {
            if (request.getCardNumber() == null || !isLuhnValid(request.getCardNumber())) {
                throw new IllegalArgumentException("Invalid credit card number.");
            }
            if (request.getExpiryDate() == null || request.getCvv() == null) {
                throw new IllegalArgumentException("Expiry date and CVV are required for credit card payments.");
            }
            // Add more validation for expiry date if needed
        }

        Payment newPayment = new Payment();
        newPayment.setUserId(request.getUserId());
        newPayment.setBookingId(request.getBookingId());
        newPayment.setAmount(request.getAmount());
        newPayment.setPaymentMethod(request.getPaymentMethod());
        newPayment.setStatus(Payment.Status.COMPLETED); // Default to COMPLETED

        Payment savedPayment = paymentRepository.save(newPayment);
        return PaymentResponseDTO.fromEntity(savedPayment);
    }

    @Override
    @Transactional
    public PaymentResponseDTO updatePaymentStatus(Integer paymentId, Payment.Status status) throws Exception {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new Exception("Payment not found with ID: " + paymentId));

        if (status != null) {
            payment.setStatus(status);
        }

        Payment updatedPayment = paymentRepository.save(payment);
        return PaymentResponseDTO.fromEntity(updatedPayment);
    }

    @Override
    @Transactional
    public void deletePayment(Integer paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found with ID: " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
    }

    /**
     * Validates a credit card number using the Luhn algorithm.
     * @param cardNumber The card number to validate.
     * @return true if the card number is valid, false otherwise.
     */
    private boolean isLuhnValid(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }
        String sanitizedCardNumber = cardNumber.replaceAll("\\s+", "");
        if (!sanitizedCardNumber.matches("\\d+")) {
            return false; // Not a number
        }

        int nDigits = sanitizedCardNumber.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = sanitizedCardNumber.charAt(i) - '0';
            if (isSecond) {
                d = d * 2;
            }
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}