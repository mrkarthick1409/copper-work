package com.example.demo.exception;

public enum ErrorCode implements ErrorHandle {

	TRAIN_SCHEDULER_1000(1000, "Error! "),
	TRAIN_SCHEDULER_1001(1001, "Invalid Train Input "),
	TRAIN_SCHEDULER_1002(1002, "Error saving train data");



	private final int code;
	private final String message;

	ErrorCode(int errorCode, String message) {
		this.code = errorCode;
		this.message = message;
	}

	@Override
	public int getErrorCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
