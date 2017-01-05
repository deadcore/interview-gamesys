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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import io.igu.gamesys.core.dao.EntityExistsException;
import io.igu.gamesys.core.dao.UserDao;
import io.igu.gamesys.core.domain.User;

/**
 * An in memory implementation of {@link UserDao} which is thread safe
 *
 * @author Jack Liddiard
 */
@Repository
public final class InMemoryUserDao implements UserDao {

	/*
	 * No protection is guarantee against checking is the 
	 */
	private final List<User> buffer = new ArrayList<>();
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(final User user) {
		Validate.notNull(user, "user must not be null");
		
		lock.readLock().lock();
		try {
			for (final User candidate : buffer) {
				if (candidate.getUsername().equals(user.getUsername())) {
					throw new EntityExistsException("user.duplicateUsername");
				}
			}
		} finally {
			lock.readLock().unlock();
		}
		
		lock.writeLock().lock();
		
		try {
			buffer.add(user);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<User> findByDateOfBirthAndSocialSecurityNumber(final LocalDate dob, final String sscn) {
		Validate.notNull(dob, "user must not be null");
		Validate.notNull(sscn, "sscn must not be null");

		for (final User i : buffer) {
			if (i.getDateOfBirth().equals(dob) && i.getSocialSecurityNumber().equals(sscn)) {
				return Optional.of(i);
			}
		}
		
		return Optional.empty();
	}

}
