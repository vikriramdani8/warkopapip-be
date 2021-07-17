package com.springboot.app.model;

public class Response {
	
	private String responseCode;
	private String responseDesc;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	
	public Response(String responseCode, String responseDesc) {
		super();
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
	}
	
	
}
