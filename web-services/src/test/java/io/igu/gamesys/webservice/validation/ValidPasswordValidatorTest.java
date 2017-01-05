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
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.igu.gamesys.webservice.validation.ValidPasswordValidator;

/**
 * A test suit surrounding {@link ValidPasswordValidator}.
 *
 * @author Jack Liddiard
 */
public class ValidPasswordValidatorTest {

	/*
	 * MUST be at least four characters
	 * MUST be at least one upper case character
	 * MUST be at least one number
	 */
	private final ValidPasswordValidator validator = new ValidPasswordValidator();
	
	/**
	 * Tests that an empty string returns false
	 */
	@Test
	public void testEmptyStringIsInvalid() {
		assertThat(validator.isValid("", null), is(false));
	}
	
	/**
	 * Tests that a string lest than 4 characters returns false
	 */
	@Test
	public void testThreeCharacterStringIsInvalid() {
		assertThat(validator.isValid("Ab1", null), is(false));
	}
	
	/**
	 * Tests that a string with 4 characters returns true
	 */
	@Test
	public void testFourCharacterStringValid() {
		assertThat(validator.isValid("Ab1a", null), is(true));
	}
	
	/**
	 * Tests that a string with no upper case characters returns false
	 */
	@Test
	public void testLackOfAtleastOneUpperCaseIsInvalid() {
		assertThat(validator.isValid("ab1a", null), is(false));
	}
	
	/**
	 * Tests that a string with no lower case characters returns false
	 */
	@Test
	public void testLackOfAtleastOneLowerCaseIsInvalid() {
		assertThat(validator.isValid("AB1A", null), is(false));
	}
	
	/**
	 * Tests that a string with no numbers returns false
	 */
	@Test
	public void testLackOfAtleastOneNumberCaseIsInvalid() {
		assertThat(validator.isValid("ABBA", null), is(false));
	}
	
	/**
	 * Tests that a null value returns false
	 */
	@Test
	public void testNullIsInvalid() {
		assertThat(validator.isValid(null, null), is(false));
	}
	
}