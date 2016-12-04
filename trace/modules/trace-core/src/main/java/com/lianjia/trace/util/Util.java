package com.lianjia.trace.util;

import static java.lang.String.format;

import java.nio.charset.Charset;

public final class Util {

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
		if (reference == null) {
			throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
		}
		return reference;
	}

	public static String checkNotBlank(String string, String errorMessageTemplate, Object... errorMessageArgs) {
		if (checkNotNull(string, errorMessageTemplate, errorMessageArgs).trim().isEmpty()) {
			throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
		}
		return string;
	}

	public static boolean equal(Object a, Object b) {
		return a == b || (a != null && a.equals(b));
	}

	private Util() {
	}
}
