package com.cab.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cab.book.model.BookingRequest;
import com.cab.book.model.BookingResponse;
import com.cab.book.model.GetAllBookingResponse;
import com.cab.book.service.BookingService;
import com.sun.istack.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("This controller contains all Booking related webservices.")
public class BookingController {
	@Autowired
	BookingService bookingService;

	@PostMapping("/bookcab")
	@ApiOperation("This webservice will book the cab")
	public BookingResponse bookCab(@NotNull @RequestBody BookingRequest bookingRequest) {
		return bookingService.bookCab(bookingRequest);
	}
	
	@GetMapping("/getAllBookings")
	@ApiOperation("This webservice will fetch all the bookings")
    public GetAllBookingResponse getAllBookings() {
		return bookingService.getAllBookings();
	}
	
	@PostMapping("/getDriverName")
	@ApiOperation("This webservice will return the nearest available driver")
	public BookingResponse getDriverName(@NotNull @RequestBody BookingRequest bookingRequest) {
		return bookingService.getDriverName(bookingRequest);
	}
}
