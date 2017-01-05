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
package io.igu.gamesys.core.service.impl;

import org.apache.commons.lang3.Validate;

/**
 * An exceptions used to denote the a person has been black listed. This can be because of a number of reasons
 * but most commonly these can be either an internal afair or an external third party.
 * <p>
 * Essentially if a user is deemed blacklisted he/she must not be allowed into the application
 * or allowed to register as they are a known to conduct misconduct. An example may (but not limited to) a cheat/hacker
 * or other form of malicious activist.
 * <h6>Example</h6>
 * <ul>
 * <li>A black listed user may have been caught cheating in a casino. A third party (Griffin Investigations) may
 * have black listed that single person</li>
 * <li>Internally a user may be black listed as a known hacker or cheater after being flagged up</li>
 * <li>Developers of the project may not be allowed due to knowing the strengths/weakness and exploit</li>
 * </ul>
 * @author Jack Liddiard
 */
public final class BlacklistedUserException extends Exception {

	/**
	 * Markup
	 * <ul>
	 * <li>String - dob</li>
	 * <li>String - socialSecurityNumber</li>
	 * </ul>
	 */
	private static final long serialVersionUID = 9096698464114050863L;
	
	private final String dob;
	private final String socialSecurityNumber;

	public BlacklistedUserException(final String dob, final String socialSecurityNumber) {
		super();
		this.dob = Validate.notNull(dob);
		this.socialSecurityNumber = Validate.notNull(socialSecurityNumber);
	}

	/**
	 * Gets the date of birth of the black listed user.
	 * @return the dob. Never {@code null}
	 */
	public final String getDob() {
		return dob;
	}

	/**
	 * Gets the social security number of the black listed user.
	 * @return the socialSecurityNumber. Never {@code null}
	 */
	public final String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
}