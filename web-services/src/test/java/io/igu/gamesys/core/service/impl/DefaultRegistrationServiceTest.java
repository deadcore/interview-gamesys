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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import com.gamesys.ExclusionService;

import io.igu.gamesys.core.dao.UserDao;
import io.igu.gamesys.core.dao.inmemory.InMemoryUserDao;
import io.igu.gamesys.core.domain.User;
import io.igu.gamesys.webservice.command.RegistrationCommand;

/**
 *
 *
 * @author Jack Liddiard
 */
public class DefaultRegistrationServiceTest {
	
	/**
	 * Tests that when a user is saved but is deemed part of the blacklist, he is not persited to any underlying datasource
	 * and a {@link BlacklistedUserException} is thrown.
	 * @throws BlacklistedUserException hopfully!
	 */
	@Test(expected=BlacklistedUserException.class)
	public void testAUserWhoIsBlackListedIsNotRegistered() throws BlacklistedUserException {
		
		final ExclusionService exclusionService = new ExclusionService() {
			@Override
			public boolean validate(final String dob, final String ssn) {
				return false;
			}
		};
		
		final DefaultRegistrationService service = new DefaultRegistrationService(exclusionService, new UserDao() {
			@Override
			public void save(final User user) {
				fail("Unexpected save encountered");
			}
			
			@Override
			public Optional<User> findByDateOfBirthAndSocialSecurityNumber(final LocalDate dob, final String sscn) {
				return Optional.empty();
			}
		});
		
		final RegistrationCommand command = new RegistrationCommand();
		
		command.setDateOfBirth(1L);
		command.setSocialSecurityNumber("218-555-112");
		
		service.register(command);
	}
	
	/**
	 * Tests that if a valid user is registered he is persited to the underlying persitance layer.
	 * @throws BlacklistedUserException
	 */
	public void testAUserValidUserIsPersited() throws BlacklistedUserException {
		
		final UserDao userDao = new InMemoryUserDao();
		
		final ExclusionService exclusionService = new ExclusionService() {
			@Override
			public boolean validate(final String dob, final String ssn) {
				return true;
			}
		};
		
		final DefaultRegistrationService service = new DefaultRegistrationService(exclusionService, userDao);
		
		final RegistrationCommand command = new RegistrationCommand();
		
		command.setUsername("MyUsername");
		command.setPassword("password");
		command.setDateOfBirth(1L);
		command.setSocialSecurityNumber("218-555-112");
		
		service.register(command);
		
		assertThat(userDao.findByDateOfBirthAndSocialSecurityNumber(LocalDate.ofEpochDay(1), "218-555-112").isPresent(), is(true));
	}
	
}