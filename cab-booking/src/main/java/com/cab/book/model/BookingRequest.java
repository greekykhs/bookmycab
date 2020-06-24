package com.cab.book.model;

public class BookingRequest {
	private String customerName;
	private Double customerLatitude;
	private Double customerLongitude;
	
	public BookingRequest() {
		super();
	}

	@Override
	public String toString() {
		return "BookingRequest [customerName=" + customerName + ", customerLatitude=" + customerLatitude
				+ ", customerLongitude=" + customerLongitude + "]";
	}

	public BookingRequest(String customerName, Double customerLatitude, Double customerLongitude) {
		super();
		this.customerName = customerName;
		this.customerLatitude = customerLatitude;
		this.customerLongitude = customerLongitude;
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
}
