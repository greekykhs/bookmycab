package com.cab.book.model;

import java.util.ArrayList;
import java.util.List;

import com.cab.book.repository.DriverView;

public class GetAllDriversResponse extends GenericResponse{
	private List<DriverView> drivers=new ArrayList<>();

	public List<DriverView> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<DriverView> drivers) {
		this.drivers = drivers;
	}	
}
