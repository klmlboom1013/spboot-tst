package com.lhs.domain;

public class Result {

	private boolean valid;
	
	private String errorMessage;
	
	private Object resultData;
	
	private Result(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	private Result(boolean valid, String errorMessage, Object resultData) {
		this.valid = valid;
		this.errorMessage = errorMessage;
		this.resultData = resultData;
	}
	
	public static Result OK() {
		return new Result(true, null);
	}
	
	public static Result OK(Object resultData) {
		return new Result(true, null, resultData);
	}
	
	public static Result fail (String errorMessage) {
		return new Result(false, errorMessage);
	}

	public boolean isValid() {
		return valid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Object getResultData() {
		return resultData;
	}	
}
