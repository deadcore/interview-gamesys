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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.Validate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Represents a data transfer object of field binding errors. This will most certainly be from
 * some for of validation error. An example would be validation failures from {@link Valid}.
 *
 * @author Jack Liddiard
 */
public final class FieldValidationBindingError implements ErrorWrapper {

	private final Collection<String> fieldErrors;

	/**
	 * Constructs a new instance of {@link FieldValidationBindingError}.
	 * @param fieldErrors the field error reference. Must not be {@code null}.
	 */
	private FieldValidationBindingError(final Collection<String> fieldErrors) {
		assert fieldErrors != null : "fieldErrors must not be null";
		
		this.fieldErrors = fieldErrors;
	}

	/**
	 * A factory method for creating a new instance of {@link FieldValidationBindingError} from a
	 * {@link BindingResult}.
	 * @param bindingResult the {@link BindingResult}. Must not be {@code null}.
	 * @return an instance of {@link FieldValidationBindingError}. Never {@code null}.
	 */
	public static FieldValidationBindingError from(final BindingResult bindingResult) {
		Validate.notNull(bindingResult, "error must not be null");
		
		final List<String> buf = new ArrayList<>();
		
		for (final FieldError err : bindingResult.getFieldErrors()) {
			buf.add(err.getField() + "." +err.getCode());
		}
		
		return new FieldValidationBindingError(buf);
	}

	/**
	 * Returns the field errors.
	 * @return the {@link String} field errors. Never {@code null}.
	 */
	public final Collection<String> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getErrorMessage() {
		return "object.fielderrors";
	}
}
