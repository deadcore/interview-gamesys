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

import javax.validation.Valid;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.igu.gamesys.core.service.RegistrationService;
import io.igu.gamesys.core.service.impl.BlacklistedUserException;
import io.igu.gamesys.webservice.command.RegistrationCommand;

/**
 * A restful endpoint that exposes the registration service.
 *
 * @author Jack Liddiard
 */
@RestController
@RequestMapping(RegistrationController.REQUEST_MAPPING)
public final class RegistrationController {

	public static final String REQUEST_MAPPING = "/register";
	
	private final RegistrationService registrationService;

	@Autowired
	public RegistrationController(final RegistrationService registrationService) {
		this.registrationService = Validate.notNull(registrationService, "registrationService must not be null");
	}

	/**
	 * The {@link RequestMethod#POST} endpoint which will consume a valid {@link RegistrationCommand}.
	 * <p>
	 * The service will attempt to consume the {@code RegistrationCommand} and transform it into a persisted user.
	 * 
	 * @param command
	 * @throws BlacklistedUserException if the user has been deemded blacklisted.
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public final void register(@Valid @RequestBody final RegistrationCommand command) throws BlacklistedUserException {
		Validate.notNull(command, "command must not be null");
		
		registrationService.register(command);
	}

}