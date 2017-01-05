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

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Service;

import com.gamesys.ExclusionService;

/**
 * The stubbed implementation of {@link ExclusionService} with two hardcoded
 * exlucsions. These are:
 * <h6>Exclusion 1</h6>
 * Date of Birth: 01/01/1994
 * Social Security Number: 111-111-111
 * <h6>Exclusion 2</h6>
 * Date of Birth: 01/01/2016
 * Social Security Number: 222-222-222
 * 
 * @author Jack Liddiard
 */
@Service
public final class StubbedExclusionService implements ExclusionService {

	private static Set<Blacklist> BLACKLIST = unmodifiableSet(new HashSet<>(asList(new Blacklist(LocalDate.of(1994, 1, 1), "111-111-111"), new Blacklist(LocalDate.of(2016, 1, 1), "222-222-222"))));

	/**
	 * ISO 8601 implementation of {@link DateTimeFormatter}
	 */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(final String dob, final String ssn) {
		return !BLACKLIST.contains(new Blacklist(LocalDate.parse(dob, FORMATTER), ssn));
	}

	/**
	 * A containing blacklist object
	 *
	 * @author Jack Liddiard
	 */
	private static final class Blacklist {
		private final LocalDate dob;
		private final String ssn;

		public Blacklist(final LocalDate dob, final String ssn) {
			assert dob != null : "dob must not be null";
			assert ssn != null : "ssn must not be null";

			this.dob = dob;
			this.ssn = ssn;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return new HashCodeBuilder()
			.append(dob)
			.append(ssn)
			.build();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Blacklist)) {
				return false;
			}
			final Blacklist other = (Blacklist) obj;
			
			return new EqualsBuilder()
			.append(this.dob, other.dob)
			.append(this.ssn, other.ssn)
			.build();
		}
	}

}
