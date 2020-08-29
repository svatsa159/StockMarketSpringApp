package com.stockmarket.backend.exception;

public class EntityNotFound extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public EntityNotFound(String x) {
		super();
		this.message = x;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
