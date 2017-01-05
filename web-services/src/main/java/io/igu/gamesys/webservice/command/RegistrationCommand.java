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
package io.igu.gamesys.webservice.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.igu.gamesys.webservice.validation.ValidPassword;
import io.igu.gamesys.webservice.validation.ValidUsername;


/**
 *
 *
 * @author Jack Liddiard
 */
public final class RegistrationCommand {

	@ValidUsername
	private String username;

	@ValidPassword
	private String password;

	@NotNull
	private Long dateOfBirth;

	@NotEmpty
	private String socialSecurityNumber;

	/**
	 * Gets the RegistrationCommand value for the username
	 * @return the username. May be {@code null}
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * Sets the RegistrationCommand value for the username
	 * @return the RegistrationCommand for the username. May be {@code null}
	 */
	public final void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Gets the RegistrationCommand value for the password
	 * @return the password. May be {@code null}
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Sets the RegistrationCommand value for the password
	 * @return the RegistrationCommand for the password. May be {@code null}
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the RegistrationCommand value for the socialSecurityNumber
	 * @return the socialSecurityNumber. May be {@code null}
	 */
	public final String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	/**
	 * Sets the RegistrationCommand value for the socialSecurityNumber
	 * @return the RegistrationCommand for the socialSecurityNumber. May be {@code null}
	 */
	public final void setSocialSecurityNumber(final String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * Gets the RegistrationCommand value for the dateOfBirth
	 * @return the dateOfBirth. May be {@code null}
	 */
	public final Long getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the RegistrationCommand value for the dateOfBirth
	 * @return the RegistrationCommand for the dateOfBirth. May be {@code null}
	 */
	public final void setDateOfBirth(final Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
