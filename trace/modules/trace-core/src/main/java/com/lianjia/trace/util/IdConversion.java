package com.lianjia.trace.util;

public class IdConversion {
	/**
	 * Converts long trace or span id to String.
	 * 
	 * @param id
	 *            trace, span or parent span id.
	 * @return String representation.
	 */
	public static String convertToString(final long id) {
		return Long.toHexString(id);
	}

	/**
	 * Converts String trace or span id to long.
	 * 
	 * @param id
	 *            trace, span or parent span id.
	 * @return Long representation.
	 */
	public static long convertToLong(final String id) {
		if (id.length() == 0 || id.length() > 16) {
			throw new NumberFormatException(id + " should be a <=16 character lower-hex string with no prefix");
		}

		long result = 0;

		for (char c : id.toCharArray()) {
			result <<= 4;

			if (c >= '0' && c <= '9') {
				result |= c - '0';
			} else if (c >= 'a' && c <= 'f') {
				result |= c - 'a' + 10;
			} else {
				throw new NumberFormatException("character " + c + " not lower hex in " + id);
			}
		}

		return result;
	}
}
