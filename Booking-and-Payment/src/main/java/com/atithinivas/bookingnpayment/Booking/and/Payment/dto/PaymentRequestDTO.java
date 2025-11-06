//package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
//import lombok.Data;
//
//
//@Data
//public class PaymentRequestDTO {
//
//    private String userId;
//    private String bookingid;
//    private Double amount;
//    private String paymentMethod;
//
//}


package com.atithinivas.bookingnpayment.Booking.and.Payment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class PaymentRequestDTO {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Booking ID is required")
    private Integer bookingId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount; // As per ER diagram 'amount INT'

    @NotEmpty(message = "Payment method is required")
    private String paymentMethod; // e.g., "CREDIT_CARD", "PAYPAL"

    // Card details (only for validation, not for saving)
    // These fields are optional and only validated if paymentMethod is "CREDIT_CARD"
    private String cardNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    @Pattern(regexp = "^[0-9]{3,4}$", message = "CVV must be 3 or 4 digits")
    private String cvv;

}
