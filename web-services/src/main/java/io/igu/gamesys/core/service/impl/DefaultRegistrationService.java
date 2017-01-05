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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesys.ExclusionService;

import io.igu.gamesys.core.dao.UserDao;
import io.igu.gamesys.core.domain.User;
import io.igu.gamesys.core.service.RegistrationService;
import io.igu.gamesys.webservice.command.RegistrationCommand;

/**
 * The default implamentation of {@link RegistrationService}
 * <p>
 * The proposed user is checked against several blacklist (for example The Griffin Book) 
 * by the {@link ExclusionService}. This user may be blacklisted and thus will not 
 * be registered. A {@link BlacklistedUserException} is thrown
 *
 * @author Jack Liddiard
 */
@Service
public final class DefaultRegistrationService implements RegistrationService {

	private final ExclusionService exclusionService;
	
	private final UserDao userDao;
	
	/**
	 * ISO 8601 implementation of {@link DateTimeFormatter}
	 */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;
	
	/**
	 * Constructs a new instance of {@link DefaultRegistrationService}.
	 * @param exclusionService the {@link ExclusionService}. Must not be {@code null}.
	 * @param userDao the {@link UserDao}. Must not be {@code null}.
	 */
	@Autowired
	public DefaultRegistrationService(final ExclusionService exclusionService, final UserDao userDao) {
		this.exclusionService = Validate.notNull(exclusionService, "exclusionService must not be null");
		this.userDao = Validate.notNull(userDao, "userdao must not be null");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BlacklistedUserException 
	 */
	@Override
	public void register(final RegistrationCommand command) throws BlacklistedUserException {
		Validate.notNull(command, "command must not be null");
		
		final LocalDate dateOfBirth = LocalDate.ofEpochDay(command.getDateOfBirth());
		final String isoDate = FORMATTER.format(dateOfBirth);
		if (exclusionService.validate(isoDate, command.getSocialSecurityNumber())) {
			userDao.save(new User(command.getUsername(), command.getPassword(), dateOfBirth, command.getSocialSecurityNumber()));
		} else {
			throw new BlacklistedUserException(isoDate, command.getSocialSecurityNumber());
		}
	}

}
