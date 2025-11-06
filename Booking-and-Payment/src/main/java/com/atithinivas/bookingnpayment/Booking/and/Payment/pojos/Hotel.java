package com.atithinivas.bookingnpayment.Booking.and.Payment.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	private String hotelId;      // UUID-style ID
    private String name;
    private String location;
    private List<String> amenities;
    private String rating;
    private String managerName; 

}
