package com.cab.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import com.cab.book.common.Constants;

@Entity
@Table(name = "driver_details")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "did")
	private Long did;

	@Column(name = "driver_name")
	private String driverName;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "status")
	private String status;
	
//	@OneToOne
//	private BookingDetails bookingDetails;
	
	public Driver() {
		super();
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public BookingDetails getBookingDetails() {
//		return bookingDetails;
//	}
//
//	public void setBookingDetails(BookingDetails bookingDetails) {
//		this.bookingDetails = bookingDetails;
//	}

	@Override
	public String toString() {
		return "Driver [did=" + did + ", driverName=" + driverName + ", latitude=" + latitude + ", longitude="
				+ longitude + ", status=" + status + "]";
	}
}
