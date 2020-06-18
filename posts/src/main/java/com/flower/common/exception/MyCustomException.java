package com.flower.common.exception;

public class MyCustomException extends Exception {
	private static final long serialVersionUID = 2356665062936385087L;

	private final int httpStatusCode;
	private final int errorCode;
	
	public MyCustomException(String message) {
        super(message);
        this.httpStatusCode = 0;
        this.errorCode = 0;
    }
	
	public MyCustomException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.errorCode = 0;
    }
	
	public MyCustomException(int httpStatusCode, int errorCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }
}
