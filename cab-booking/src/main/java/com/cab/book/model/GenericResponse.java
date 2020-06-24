package com.cab.book.model;

public class GenericResponse {
	private String applicationCode;
	private String applicationResponse;
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplicationResponse() {
		return applicationResponse;
	}
	public void setApplicationResponse(String applicationResponse) {
		this.applicationResponse = applicationResponse;
	}
	public GenericResponse() {
		super();
	}
	public GenericResponse(String applicationCode, String applicationResponse) {
		super();
		this.applicationCode = applicationCode;
		this.applicationResponse = applicationResponse;
	}
}
