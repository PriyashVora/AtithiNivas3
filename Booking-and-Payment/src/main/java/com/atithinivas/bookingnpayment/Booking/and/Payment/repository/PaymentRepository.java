//package com.atithinivas.bookingnpayment.Booking.and.Payment.repository;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface PaymentRepository extends JpaRepository<Payment, String> { // ID type is String
//    List<Payment> findByUserId(String userId);
//    List<Payment> findByBookingid(String bookingid); // Matches the field name
//}




package com.atithinivas.bookingnpayment.Booking.and.Payment.repository;

import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> { // ID type is Integer
    List<Payment> findByUserId(Integer userId);
    List<Payment> findByBookingId(Integer bookingId); // Renamed from findByBookingid
}