//package com.atithinivas.bookingnpayment.Booking.and.Payment;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class BookingAndPaymentApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BookingAndPaymentApplication.class, args);
//	}
//
//}


package com.atithinivas.bookingnpayment.Booking.and.Payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atithinivas.bookingnpayment.Booking.and.Payment.client")
public class BookingAndPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingAndPaymentApplication.class, args);
    }

}