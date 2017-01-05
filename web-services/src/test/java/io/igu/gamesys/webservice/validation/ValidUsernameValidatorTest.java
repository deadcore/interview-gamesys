/*
 * Copyright (c) 2016 igu.io. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of igu.io.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with igu.io.
 *
 * IGU.IO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. NJW SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package io.igu.gamesys.webservice.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * A test suit surrounding {@link ValidUsernameValidator}
 *
 * @author Jack Liddiard
 */
public class ValidUsernameValidatorTest {
	/*
	 * MUST only allow alphanumerical [a-Z0-9]*
	 * MUST not allow spaces
	 */
	private final ValidUsernameValidator validator = new ValidUsernameValidator();

	/**
	 * Tests that the alphanumeric space is valid
	 */
	@Test
	public void testValidAlphanumericalCharacters() {
		final char[] alphanumerical = new char[] { 
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', //Lowercase space
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', //Uppercase space
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' //Numbers
		};
		
		for (final char c : alphanumerical) {
			assertTrue("Expected character ["+c+"] to be valid", validator.isValid(String.valueOf(c), null));
		}
	}

	/**
	 * Tests that compound number (example 10) is valid
	 */
	@Test
	public void testCompoundNumbers() {
		assertTrue("Expected character [10] to be valid", validator.isValid("10", null));
	}

	/**
	 * Tests that a null string is invalid
	 */
	@Test
	public void testNull() {
		assertThat(validator.isValid(null, null), is(false));
	}

	/**
	 * Tests that a blank string is invalid
	 */
	@Test
	public void testBlank() {
		assertThat(validator.isValid("", null), is(false));
	}

	/**
	 * Tests that a number of special characters are invalid
	 */
	@Test
	public void testSpecialCharacters() {
		final char[] special = new char[] { '!', '"', '£', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '`', '¬', '\\', '|', '<', ',', '.', '>', '/', '?', ' ' };
		for (final char c : special) {
			assertFalse("Expected character ["+c+"] to be invalid", validator.isValid(String.valueOf(c), null));
		}
	}
}
