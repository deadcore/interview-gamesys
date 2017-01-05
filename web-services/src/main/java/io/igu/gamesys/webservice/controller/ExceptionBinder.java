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

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.igu.gamesys.core.dao.EntityExistsException;
import io.igu.gamesys.core.service.impl.BlacklistedUserException;

/**
 * Binds any exceptions spring has resolved into a repsonse to the client.
 *
 * @author Jack Liddiard
 */
@ControllerAdvice
public final class ExceptionBinder extends ResponseEntityExceptionHandler {

	/**
	 * {@inheritDoc}
	 * Handles the form binding exceptions
	 */
	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return super.handleExceptionInternal(ex, FieldValidationBindingError.from(ex.getBindingResult()), headers, status, request);
	}

	/**
	 * Handles a {@link BlacklistedUserException} by binding them to a 403 forbidden.
	 * @param ex the {@link BlacklistedUserException}. Must not be {@code null}.
	 * @param request the {@link WebRequest}. Must not be {@code null}.
	 * @return a {@code ResponseEntity} instance. May be {@code null}
	 */
	@ExceptionHandler(BlacklistedUserException.class)
	public final ResponseEntity<Object> handleBlacklistedUserException(final BlacklistedUserException ex, final WebRequest request) {
		Validate.notNull(ex, "ex must not be null");
		Validate.notNull(request, "request must not be null");

		return handleExceptionInternal(ex, BlacklistedUserError.from(ex), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

	/**
	 * Handles a {@link EntityExistsException} by binding them to a 409 forbidden. The exeception error message
	 * is used as the message key.
	 * @param ex the {@link EntityExistsException}. Must not be {@code null}.
	 * @param request the {@link WebRequest}. Must not be {@code null}.
	 * @return a {@code ResponseEntity} instance. May be {@code null}
	 */
	@ExceptionHandler(EntityExistsException.class)
	public final ResponseEntity<Object> handleEntityExistsException(final EntityExistsException ex, final WebRequest request) {
		Validate.notNull(ex, "ex must not be null");
		Validate.notNull(request, "request must not be null");
		
		return handleExceptionInternal(ex, new ErrorWrapper() {
			@Override
			public String getErrorMessage() {
				return ex.getMessage();
			}
		}, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}