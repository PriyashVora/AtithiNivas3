//package com.atithinivas.bookingnpayment.Booking.and.Payment.repository;
//
//import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface BookingRepository extends JpaRepository<Booking, String> { // ID type is String
//    List<Booking> findByUserId(String userId);
//    List<Booking> findByRoomId(String roomId);
//    List<Booking> findByHotelId(String hotelId);
//}


package com.atithinivas.bookingnpayment.Booking.and.Payment.repository;

import com.atithinivas.bookingnpayment.Booking.and.Payment.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> { // ID type is Integer
    List<Booking> findByUserId(Integer userId);
    List<Booking> findByRoomId(Integer roomId);
    List<Booking> findByHotelId(String hotelId);
}