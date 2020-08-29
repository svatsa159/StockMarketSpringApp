package com.stockmarket.spreadsheet.exception;

public class ExcelFormatErrorException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ExcelFormatErrorException(String x) {

		this.message = x;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
