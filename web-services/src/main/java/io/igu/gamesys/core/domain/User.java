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
package io.igu.gamesys.core.domain;

import java.time.LocalDate;

import org.apache.commons.lang3.Validate;

/**
 * A user is defined as a physical person. Currently we define them by their socical security number
 * and their date of birth.
 * <p>
 * Other meta data held against the user is their username and password.
 *
 * @author Jack Liddiard
 */
public final class User {

	private final String username;
	private final String password;
	private final LocalDate dateOfBirth;
	private final String socialSecurityNumber;

	public User(final String username, final String password, final LocalDate dateOfBirth, final String socialSecurityNumber) {
		this.username = Validate.notNull(username, "Username must not be null");
		this.password = Validate.notNull(password, "Password must not be null");
		this.dateOfBirth = Validate.notNull(dateOfBirth, "dateOfBirth must not be null");
		this.socialSecurityNumber = Validate.notNull(socialSecurityNumber, "socialSecurityNumber must not be null");
	}

	/**
	 * Gets the User value for the username
	 * @return the username. Never {@code null}
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * Gets the User value for the password
	 * @return the password. Never {@code null}
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Gets the User value for the dateOfBirth
	 * @return the dateOfBirth. Never {@code null}
	 */
	public final LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Gets the User value for the socialSecurityNumber
	 * @return the socialSecurityNumber. Never {@code null}
	 */
	public final String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
}
