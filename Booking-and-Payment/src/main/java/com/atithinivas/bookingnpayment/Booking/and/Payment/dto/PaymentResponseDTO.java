//package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
//import lombok.Data;
//
//
//@Data
//public class PaymentResponseDTO {
//
//    private String paymentId;
//    private String userId;
//    private String bookingid;
//    private Double amount;
//    private Payment.Status status;
//    private String paymentMethod;
//
//    public static PaymentResponseDTO fromEntity(Payment payment) {
//        PaymentResponseDTO dto = new PaymentResponseDTO();
//        dto.setPaymentId(payment.getPaymentId());
//        dto.setUserId(payment.getUserId());
//        dto.setBookingid(payment.getBookingid());
//        dto.setAmount(payment.getAmount());
//        dto.setStatus(payment.getStatus());
//        dto.setPaymentMethod(payment.getPaymentMethod());
//        return dto;
//    }
//}





package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;

import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PaymentResponseDTO {

    private Integer paymentId;
    private Integer userId;
    private Integer bookingId;
    private Integer amount;
    private Payment.Status status;
    private String paymentMethod;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long version;

    private String createdBy; // <-- ADD THIS
    private String updatedBy; // <-- ADD THIS




    public static PaymentResponseDTO fromEntity(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setCreatedBy(payment.getCreatedBy()); // <-- ADD THIS
        dto.setUpdatedBy(payment.getUpdatedBy());
        dto.setUserId(payment.getUserId());
        dto.setBookingId(payment.getBookingId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setCreatedOn(payment.getCreatedOn());
        dto.setUpdatedOn(payment.getUpdatedOn());
        dto.setVersion(payment.getVersion());
        return dto;
    }
}