package com.cab.book.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cab.book.common.Constants;
import com.cab.book.model.Driver;
import com.cab.book.model.GetAllDriversResponse;
import com.cab.book.repository.DriverRepository;
import com.cab.book.repository.DriverView;
@Service
public class DriverServiceImpl implements DriverService {
	public static final Logger logger = LogManager.getLogger();
	@Autowired
	@Lazy
	DriverRepository driverRepository;
	
	@Override
	public GetAllDriversResponse getAllDrivers() {
		logger.info("*****DriverServiceImpl: inside getAllDrivers");
		GetAllDriversResponse getAllDriversResponse=new GetAllDriversResponse();
		List<DriverView> driverViews=null;
		try {
			driverViews=driverRepository.findAllDrivers();
		} catch (Exception e) {
			logger.error("*****DriverServiceImpl: inside getAllDrivers(). Exception occurred while fetching the drivers.");
		}
		if(driverViews==null || driverViews.isEmpty()) {
			getAllDriversResponse.setApplicationCode(Constants.Responses.NO_DRIVER_FOUND.getErrorCode());
			getAllDriversResponse.setApplicationResponse(Constants.Responses.NO_DRIVER_FOUND.getErrorMessage());
		}
		else {
			getAllDriversResponse.setDrivers(driverViews);
			getAllDriversResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
			getAllDriversResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
		}
		return getAllDriversResponse;
	}

}
