package com.cab.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cab.book.model.GetAllDriversResponse;
import com.cab.book.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("This controller contains all Driver related webservices.")
public class DriverController {
	@Autowired
	DriverService driverService;
	
	@GetMapping("/getAllDrivers")
	@ApiOperation("This webservice will fetch all the bookings")
    public GetAllDriversResponse getAllDrivers() {
		return driverService.getAllDrivers();
	}
}
