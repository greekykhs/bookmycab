package com.cab.book.model;

import java.util.Date;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "booking_details")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_number")
	private int orderNumber;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_latitude")
	private Double customerLatitude;

	@Column(name = "customer_longitude")
	private Double customerLongitude;
	
	@OneToOne
	@JoinColumn(name = "driver_did")
	private Driver driver;
	
	@Column(name = "booking_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;
	
	public static BookingDetails getBookingDtlsFromRequest(BookingRequest bookingRequest) {
		Function<BookingRequest, BookingDetails> requestToBookingDetails 
		= new Function<BookingRequest, BookingDetails>() {
			public BookingDetails apply(BookingRequest bookingRq) {
				BookingDetails bookingDetails = new BookingDetails();
				bookingDetails.setCustomerName(bookingRq.getCustomerName());
				bookingDetails.setCustomerLongitude(bookingRq.getCustomerLongitude());
				bookingDetails.setCustomerLatitude(bookingRq.getCustomerLatitude());

				return bookingDetails;
			}
		};		
		return requestToBookingDetails.apply(bookingRequest);
	}
	
	public BookingDetails() {
		super();
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getCustomerLatitude() {
		return customerLatitude;
	}

	public void setCustomerLatitude(Double customerLatitude) {
		this.customerLatitude = customerLatitude;
	}

	public Double getCustomerLongitude() {
		return customerLongitude;
	}

	public void setCustomerLongitude(Double customerLongitude) {
		this.customerLongitude = customerLongitude;
	}
	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}	
	
}
