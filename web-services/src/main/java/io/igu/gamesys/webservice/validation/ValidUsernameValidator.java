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
 * Responsible for validating any {@link String} bound to a {@link ValidUsername} annotation.
 *
 * @author Jack Liddiard
 */
public final class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {

	/**
	 * A set of safe characters to use.
	 */
	private static final char[] SAFE = new char[] { 
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', //Lowercase space
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', //Uppercase space
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' //Numbers
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(final ValidUsername constraintAnnotation) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		return !StringUtils.isBlank(value) && StringUtils.containsOnly(value, SAFE);
	}

}