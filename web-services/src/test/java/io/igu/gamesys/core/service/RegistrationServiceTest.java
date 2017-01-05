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
package io.igu.gamesys.core.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.igu.gamesys.AbstractTest;
import io.igu.gamesys.core.dao.EntityExistsException;
import io.igu.gamesys.core.service.impl.BlacklistedUserException;
import io.igu.gamesys.webservice.command.RegistrationCommand;

/**
 * A test suit surrounding {@link RegistrationService}.
 *
 * @author Jack Liddiard
 */
public class RegistrationServiceTest extends AbstractTest {

	@Autowired
	private RegistrationService registrationService;
	
	/**
	 * Tests that an already user which matches a {@link RegistrationCommand} throws an
	 * {@link EntityExistsException}.
	 * @throws BlacklistedUserException
	 */
	@Test(expected=EntityExistsException.class)
	public void testAlreadyRegisteredUserDoesNotGetPersited() throws BlacklistedUserException {
		
		final RegistrationCommand command = new RegistrationCommand();
		
		command.setDateOfBirth(1L);
		command.setPassword("PassW0rd");
		command.setSocialSecurityNumber("111-111-111");
		command.setUsername("jackliddiard");
		
		registrationService.register(command);
		registrationService.register(command);
	}
	
}
