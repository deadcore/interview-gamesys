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
package io.igu.gamesys.core.dao;

import java.time.LocalDate;
import java.util.Optional;

import io.igu.gamesys.core.domain.User;

/**
 * Represents a data access object for a {@link User}.
 *
 * @author Jack Liddiard
 */
public interface UserDao {
	
	/**
	 * Returns an {@link Optional} {@link User} searched by their date of birth and their social security number.
	 * Both conditions must hold up for a user to be deemed valid and returned.
	 * 
	 * @param dob the {@link LocalDate} of their date of birth. Must not be {@code null}.
	 * @param sscn the {@link String} social security number. Must not be {@code null}.
	 * @return an {@link Optional}. Never {@code null}.
	 */
	Optional<User> findByDateOfBirthAndSocialSecurityNumber(LocalDate dob, String sscn);
	
	/**
	 * Persists the {@link User} object to the underlying data store.
	 * 
	 * @param user the {@link User}. Must not be {@code null}.
	 */
	void save(User user) throws EntityExistsException;
	
}
