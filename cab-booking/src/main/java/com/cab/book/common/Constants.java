package com.cab.book.common;

public class Constants {
	public static final String DRIVER_STATUS_BUSY="BUSY";
	public static final String DRIVER_STATUS_AVAILABLE="AVAILABLE";
	
	public enum Responses{SUCCESS("200", "Success"),
		INTERNAL_ERROR("500", "Internal error occurred"), 
		DRIVERS_NOT_AVAILABLE("501", "Sorry cabs are not available at this time."),
		BOOKING_FAILED("502", "Error occurred while booking the cab. Please contact our customer service."),
		NO_BOOKING_FOUND("503", "No one has booked the cabs."),
		NO_DRIVER_FOUND("504", "Drivers not available"),
		;
		private String errorCode;
		private String errorMessage;
		
		private Responses(String errorCode, String errorMessage)
		{
			this.errorCode=errorCode;
			this.errorMessage=errorMessage;
		}
		
		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
	}
}
