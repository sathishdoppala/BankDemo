package com.bank.demo.exception;

public class TransferHistoryNotFoundException extends RuntimeException {
	public TransferHistoryNotFoundException(String message) {
		super(message);
	}

}
