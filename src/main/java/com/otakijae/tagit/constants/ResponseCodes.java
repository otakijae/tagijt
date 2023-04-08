package com.otakijae.tagit.constants;

public enum ResponseCodes {
	SUCCESS(200),
	NO_SUCH_DATA(404),
	DUPLICATE_KEY(400),
	NULL_VALUE(-3),
	UNKNOWN(-99);

	private final int value;

	ResponseCodes(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
