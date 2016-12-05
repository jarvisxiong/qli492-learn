package com.lianjia.trace;

public final class KeyValueAnnotation {
	private final String key;
	private final String value;

	public static KeyValueAnnotation create(String key, String value) {
		return new KeyValueAnnotation(key, value);
	}

	KeyValueAnnotation(String key, String value) {
		if (key == null) {
			throw new NullPointerException("Null key");
		}
		this.key = key;
		if (value == null) {
			throw new NullPointerException("Null value");
		}
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}