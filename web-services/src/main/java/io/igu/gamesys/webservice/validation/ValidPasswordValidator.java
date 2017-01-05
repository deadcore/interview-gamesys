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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * Validates that a {@link String} password adhears to the following contract.
 * <p>
 * <ul>
 * <li><strong>MUST</strong> be at least four characters</li>
 * <li><strong>MUST</strong> be at least one upper case character</li>
 * <li><strong>MUST</strong> be at least one number</li>
 * </ul>
 *
 * @author Jack Liddiard
 */
public final class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

	private static final char[] LOWERCASE = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] UPPERCASE = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] NUMBERS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(final ValidPassword constraintAnnotation) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		return value != null && value.length() > 3 && StringUtils.containsAny(value, UPPERCASE) && StringUtils.containsAny(value, LOWERCASE) && StringUtils.containsAny(value, NUMBERS);
	}

}