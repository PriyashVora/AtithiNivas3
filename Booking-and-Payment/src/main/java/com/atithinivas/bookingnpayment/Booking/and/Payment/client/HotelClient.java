package com.atithinivas.bookingnpayment.Booking.and.Payment.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atithinivas.bookingnpayment.Booking.and.Payment.pojos.Hotel;

@FeignClient(name = "hotelService")
public interface HotelClient {
	
	@GetMapping("api/v1/hotels/{id}")
    Optional<Hotel> getHotelById(@PathVariable String id);

}
