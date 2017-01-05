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

import io.igu.gamesys.core.domain.User;
import io.igu.gamesys.core.service.impl.BlacklistedUserException;
import io.igu.gamesys.webservice.command.RegistrationCommand;

/**
 * A service responsible for registering a {@link User}.
 * <p>
 * Deeper validation is generally performed within the service layer
 * and appropriate exceptions will be thrown in this case, unless otherwise
 * stated on the method. However this is completly optional an up to the underlying
 * implentation to uphold.
 *
 * @author Jack Liddiard
 */
public interface RegistrationService {

	/**
	 * Transforms a {@link RegistrationCommand} into a {@link User} and persists
	 * it to the underlying data store.
	 * 
	 * @param command the {@link RegistrationCommand}. Must not be {@code null}.
	 * @throws BlacklistedUserException 
	 */
	void register(RegistrationCommand command) throws BlacklistedUserException;

}
