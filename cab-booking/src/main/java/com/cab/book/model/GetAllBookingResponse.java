package com.cab.book.model;

import java.util.ArrayList;
import java.util.List;

public class GetAllBookingResponse extends GenericResponse{
	private List<BookingDetails> bookingDetails=new ArrayList<>();

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
}
