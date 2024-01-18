package com.hyundai.pms.response;

public class Response {
	
	private int code;
	private String message;
	private Object data;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Response(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
