package com.pallycon.sample.util;
/*
 * Description :
 * 
 *   This file contains a static class which handles Base64 encoding.
 * 
 * Since :
 * 
 *   2007.10.20
 * 
 * Author :
 * 
 *   JO Hyeong-ryeol (http://www.hyeongryeol.com/6)
 * 
 * Copyright :
 * 
 *   Permission to copy, use, modify, sell and distribute this software is granted provided this
 *   copyright notice appears in all copies. This software is provided "as is" without express
 *   or implied warranty, and with no claim as to its suitability for any purpose.
 *   
 *   Copyright (C) 2007 by JO Hyeong-ryeol.
 * 
 * $Id: Base64Encoder.java 59 2007-12-12 12:32:33Z JO Hyeong-ryeol $
 * 
 */

/**
 * This static class handles Base64 encoding.
 * 
 * @author JO Hyeong-ryeol
 */
public final class Base64Encoder {
	private static final char BASE64_PADDING = '=';
	private static final String BASE64_CHARS
						= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static final String BASE64__URL_CHARS
			= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

	private Base64Encoder() {
	}

	/**
	 * Converts a binary data into a Base64 encoded string.
	 * If the binary data is null or a zero-length array, an empty string is returned. 
	 * 
	 * @param value A byte array to encode. 
	 *              If it is null or a zero-length array, an empty string is returned. 
	 * @return A converted Base64 string.
	 */
	public static String encode(byte[] value) {
		if (value == null || value.length == 0)
			return "";

		// Get a required number of characters.
		int requiredSize = (value.length / 3) * 4;
		if (value.length % 3 > 0)
			requiredSize += 4;

		// Allocate memory
		char[] output = new char[requiredSize];

		// Converts the byte array into a Base64 string. (3 bytes to 4 characters)
		int outputIndex = 0, bufferIndex = 0;
		int[] buffer = new int[3];
		int base64CharIndex;

		for (int i = 0; i < value.length; i++) {
			// Accumulate bytes in buffer.
			buffer[bufferIndex++] = 0xFF & value[i];

			// If 3 bytes are collected or the last byte is collected
			if (bufferIndex == 3 || i == (value.length - 1)) {
				// Make a first character
				base64CharIndex = buffer[0] >>> 2;
				output[outputIndex++] = BASE64_CHARS.charAt(base64CharIndex);

				// Make a second character
				base64CharIndex = ((buffer[0] & 0x03) << 4) | (buffer[1] >>> 4);
				output[outputIndex++] = BASE64_CHARS.charAt(base64CharIndex);

				// Make a third character
				base64CharIndex = ((buffer[1] & 0x0F) << 2) | (buffer[2] >>> 6);
				output[outputIndex++] = bufferIndex > 1 ? BASE64_CHARS
						.charAt(base64CharIndex) : BASE64_PADDING;

				// Make a fourth character
				base64CharIndex = buffer[2] & 0x3F;
				output[outputIndex++] = bufferIndex > 2 ? BASE64_CHARS
						.charAt(base64CharIndex) : BASE64_PADDING;

				// Reset variables related to the buffer
				bufferIndex = 0;
				for (int j = 0; j < buffer.length; j++)
					buffer[j] = 0;
			}
		}

		// Return as a string
		return new String(output);
	}
}
