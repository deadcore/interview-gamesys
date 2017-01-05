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
package io.igu.gamesys.core.dao.inmemory;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import io.igu.gamesys.core.dao.EntityExistsException;
import io.igu.gamesys.core.domain.User;

/**
 *
 *
 * @author Jack Liddiard
 */
public class InMemoryUserDaoTest {
	
	private InMemoryUserDao userDao;
	
	@Before
	public void init() {
		userDao = new InMemoryUserDao();
	}
	
	/**
	 * Tests that exact same entities but with different instance of a user object are added, the secound one gets rejected.
	 */
	@Test(expected=EntityExistsException.class)
	public void testExactDuplicateCreatedUserThrowsException() {
		userDao.save(new User("conflict", "myPassword1", LocalDate.of(1994, 1, 1), "111-111-111"));
		userDao.save(new User("conflict", "myPassword1", LocalDate.of(1994, 1, 1), "111-111-111"));
	}
	
}
