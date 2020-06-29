package com.cab.book.service;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.cab.book.common.Constants;
import com.cab.book.model.BookingDetails;
import com.cab.book.model.BookingRequest;
import com.cab.book.model.BookingResponse;
import com.cab.book.model.Driver;
import com.cab.book.model.GetAllBookingResponse;
import com.cab.book.repository.BookingRepository;
import com.cab.book.repository.DriverRepository;

@Service
public class BookingServiceImpl implements BookingService {
	public static final Logger logger = LogManager.getLogger();
	@Autowired
	@Lazy
	DriverRepository driverRepository;
	
	@Autowired
	@Lazy
	BookingRepository bookingRepository;
	
	Lock lock=new ReentrantLock();
	 
	@Override
	@Transactional
	/*
	 * 	1). Customer will send the request with Customer Name, Customer Latitude, Customer Longitude.
		2). Nearest available driver is fetched from Driver table.
		3). Details will be inserted in Order table (Customer Name, Customer Latitude, Customer Longitude, Order Id, Driver Id).
		4). Driver status in the Driver table will be changed to BUSY.
		5). Return the booking status and driver details (if booking is successful)
		
		TODO: Best way to implement this is by writing a stored procedure, which will perform step 2,3 and 4.
		The procedure will fetch the nearby driver, book the cab and change the driver status as busy.
	 * */
	public BookingResponse bookCab(BookingRequest bookingRequest) {
		BookingResponse bookingResponse=new BookingResponse();		
		Driver driver=null;		
		logger.info("*****BookingServiceImpl: inside bookCab(), "+bookingRequest);		
		try {		
			lock.lock();
			//get the nearest driver
			driver = driverRepository.findNearByDriver(bookingRequest.getCustomerLatitude(),
					bookingRequest.getCustomerLongitude());
			
			if(driver==null) {
				//send error
				bookingResponse.setApplicationCode(Constants.Responses.DRIVERS_NOT_AVAILABLE.getErrorCode());
				bookingResponse.setApplicationResponse(Constants.Responses.DRIVERS_NOT_AVAILABLE.getErrorMessage());
			}
			else {
				bookingResponse=bookCab(bookingRequest, driver);			
			}
		} catch (Exception e) {
			logger.error("*****BookingServiceImpl: inside bookCab(). Exception occurred "
					+ "while fetching the driver for:" + bookingRequest.getCustomerName());
		}
		finally {
			lock.unlock();
		}
		return bookingResponse;
	}
	
	@Transactional
	public BookingResponse bookCab(BookingRequest bookingRequest, Driver driver) {
		BookingResponse bookingResponse=new BookingResponse();
		try {
			BookingDetails bookingDetails=BookingDetails.getBookingDtlsFromRequest(bookingRequest);
			bookingDetails.setDriver(driver);
			//book cab
			bookingRepository.save(bookingDetails);
			
			//update driver status to busy;
			//driver.setBookingDetails(bookingDetails);
			driverRepository.updateStatus(driver.getDid(), Constants.DRIVER_STATUS_BUSY);
			
			bookingResponse.setDriverName(driver.getDriverName());
			bookingResponse.setCustomerName(bookingRequest.getCustomerName());
			bookingResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
			bookingResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());			
		}
		catch(Exception e) {
			logger.error("*****BookingServiceImpl: inside bookCab(). Exception occurred "
					+ "while booking the driver for:"+bookingRequest.getCustomerName());
			
			bookingResponse.setApplicationCode(Constants.Responses.BOOKING_FAILED.getErrorCode());
			bookingResponse.setApplicationResponse(Constants.Responses.BOOKING_FAILED.getErrorMessage());
		}
		return bookingResponse;
	}

	@Override
	public BookingResponse getDriverName(BookingRequest bookingRequest) {
		BookingResponse bookingResponse=new BookingResponse();		
		Driver driver=null;		
		logger.info("*****BookingServiceImpl: inside bookCab(), "+bookingRequest);
		bookingResponse.setCustomerName(bookingRequest.getCustomerName());
		try {
			// get the nearest driver
			driver = driverRepository.findNearByDriver(bookingRequest.getCustomerLatitude(),
					bookingRequest.getCustomerLongitude());
		} catch (Exception e) {
			logger.error("*****BookingServiceImpl: inside bookCab(). Exception occurred "
					+ "while fetching the driver for:" + bookingRequest.getCustomerName());
		}
		if(driver==null){
			//send error
			bookingResponse.setApplicationCode(Constants.Responses.DRIVERS_NOT_AVAILABLE.getErrorCode());
			bookingResponse.setApplicationResponse(Constants.Responses.DRIVERS_NOT_AVAILABLE.getErrorMessage());
		}
		else {
			bookingResponse.setDriverName(driver.getDriverName());
			bookingResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
			bookingResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
		}
		return bookingResponse;
	}

	@Override
	public GetAllBookingResponse getAllBookings() {
		logger.info("*****BookingServiceImpl: inside getAllBookings");
		GetAllBookingResponse getAllBookingResponse=new GetAllBookingResponse();
		List<BookingDetails> bookingDetails=null;
		try {
			// get the nearest driver
			bookingDetails = bookingRepository.findAll();
		} catch (Exception e) {
			logger.error("*****BookingServiceImpl: inside getAllBookings(). Exception occurred while fetching the bookings");
		}
		if(bookingDetails==null || bookingDetails.isEmpty()) {
			getAllBookingResponse.setApplicationCode(Constants.Responses.NO_BOOKING_FOUND.getErrorCode());
			getAllBookingResponse.setApplicationResponse(Constants.Responses.NO_BOOKING_FOUND.getErrorMessage());
		}
		else {
			getAllBookingResponse.setBookingDetails(bookingDetails);
			getAllBookingResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
			getAllBookingResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
		}
		return getAllBookingResponse;
	}
}
