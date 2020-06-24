package com.cab.book;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cab.book.common.Constants;
import com.cab.book.model.BookingRequest;
import com.cab.book.model.BookingResponse;
import com.cab.book.model.GetAllBookingResponse;
import com.cab.book.service.BookingService;

@SpringBootTest
class CabBookingApplicationTests {
	@Autowired
	BookingService bookingService;
	
	@Test
	void getNearestDriverName_BeforeBooking() {
		BookingRequest bookingRequest=new BookingRequest("Divyang Bhimani", 19.103938, 72.888382);
		BookingResponse bookingResponse=bookingService.getDriverName(bookingRequest);		
		Assert.assertEquals(true, (bookingResponse!=null 
				&& "Dinesh".equals(bookingResponse.getDriverName())));
	}
	
	@Test
	void bookCab_First() {
		BookingRequest bookingRequest=new BookingRequest("Divyang Bhimani", 19.103938, 72.888382);
		BookingResponse bookingResponse=bookingService.bookCab(bookingRequest);		
		Assert.assertEquals(true, (bookingResponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(bookingResponse.getApplicationCode())));
	}
	
	@Test
	void getNearestDriverName_AfterBooking() {
		BookingRequest bookingRequest = new BookingRequest("Divyang Bhimani", 19.103938, 72.888382);
		BookingResponse bookingResponse = bookingService.getDriverName(bookingRequest);
		Assert.assertEquals(true, (bookingResponse != null && !"Dinesh".equals(bookingResponse.getDriverName())));
	}
	
	@Test
	void bookCab_Second() {
		BookingRequest bookingRequest=new BookingRequest("Himanshu Shukla", 19.1205232, 72.8823594);
		BookingResponse bookingResponse=bookingService.bookCab(bookingRequest);		
		Assert.assertEquals(true, (bookingResponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(bookingResponse.getApplicationCode())));
	}
	
	@Test
	void getAllBookings() {
		GetAllBookingResponse getAllBookingResponse=bookingService.getAllBookings();
		Assert.assertEquals(true, (getAllBookingResponse!=null 
				&& Constants.Responses.SUCCESS.getErrorCode().equals(getAllBookingResponse.getApplicationCode())
				&& getAllBookingResponse.getBookingDetails().size()>0));
	}
}
