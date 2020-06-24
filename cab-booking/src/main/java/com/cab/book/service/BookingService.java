package com.cab.book.service;

import com.cab.book.model.BookingRequest;
import com.cab.book.model.BookingResponse;
import com.cab.book.model.GetAllBookingResponse;

public interface BookingService {
	BookingResponse bookCab(BookingRequest bookingRequest);
	BookingResponse getDriverName(BookingRequest bookingRequest);
	GetAllBookingResponse getAllBookings();
}
