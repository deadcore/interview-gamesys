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
package io.igu.gamesys.webservice.controller;

import org.apache.commons.lang3.Validate;

import io.igu.gamesys.core.service.impl.BlacklistedUserException;

/**
 * A data transfer object for a blacklisted user.
 *
 * @author Jack Liddiard
 */
public final class BlacklistedUserError implements ErrorWrapper {

	private final String dob;
	private final String socialSecurityNumber;
	
	/**
	 * Constructs a new instance of {@link BlacklistedUserError}.
	 * @param dob the {@link String} date of birth. While not specific ISO 8601 format should 
	 * be used. Must not be {@code null}.
	 * @param socialSecurityNumber the social security number. Must not be {@code null}.
	 */
	private BlacklistedUserError(final String dob, final String socialSecurityNumber) {
		assert dob != null : "dob must not be null";
		assert socialSecurityNumber != null : "socialSecurityNumber must not be null";

		this.dob = dob;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getErrorMessage() {
		return "user.blacklisted";
	}
	
	/**
	 * A factory method used to convert a {@link BlacklistedUserException} into
	 * a {@link BlacklistedUserError} dto.
	 * @param e the {@link BlacklistedUserException}. Must not be {@code null}.
	 * @return an instance of {@link BlacklistedUserError}. Never {@code null}.
	 */
	public static BlacklistedUserError from(final BlacklistedUserException e) {
		Validate.notNull(e, "e must not be null");
		
		return new BlacklistedUserError(e.getDob(), e.getSocialSecurityNumber());
	}

	/**
	 * Gets the BlacklistedUserError value for the dob
	 * @return the dob. May be {@code null}
	 */
	public final String getDob() {
		return dob;
	}

	/**
	 * Gets the BlacklistedUserError value for the socialSecurityNumber
	 * @return the socialSecurityNumber. May be {@code null}
	 */
	public final String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

}
