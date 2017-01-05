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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.text.IsEmptyString;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * A set of end to end integration tests surrounding the registration controller.
 *
 * @author Jack Liddiard
 */
//Supress those pesky warnings ¬_¬
@SuppressWarnings({"unchecked", "rawtypes"})
public class RegistrationControllerIT {

	private final URI endpoint = URI.create("http://localhost:8080/register");

	private final RestOperations restOperations;

	public RegistrationControllerIT() {
		final RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		// We want to suppress any errors we hit as we may want to test negative test cases.
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(final ClientHttpResponse response) throws IOException {
				return false;
			}
			@Override
			public void handleError(final ClientHttpResponse response) throws IOException {
			}
		});
		
		this.restOperations = restTemplate;
	}
	
	/**
	 * Tests that when an empty payload is submitted the system returns a 400
	 * error indicating the request is not valid.
	 * <p>
	 * We also assert that the errors are placed into the response as expected.
	 */
	@Test
	public void testRegistrationOnEmptyPayload() {

		final MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(payload, headers);

		final ResponseEntity<Map> result = restOperations.exchange(endpoint, HttpMethod.POST, entity, Map.class);

		// Downcast to the underlying collection
		final Collection<String> fieldErrors = (Collection<String>) result.getBody().get("fieldErrors");
		final String errorMessage = (String) result.getBody().get("errorMessage");

		assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(fieldErrors, containsInAnyOrder("socialSecurityNumber.NotEmpty", "username.ValidUsername", "password.NotEmpty", "password.ValidPassword", "username.NotEmpty", "dateOfBirth.NotNull"));
		assertThat(errorMessage, is(equalTo("object.fielderrors")));
	}
	
	/**
	 * Tests that if valid body is submitted BUT a blacklisted user is specified
	 * the endpoint returns a 403 forbidden hinting the request is not allowed
	 * under the specified context
	 */
	@Test
	public void testBlacklistUserThrowsForbidenError() {

		final Map<String, Object> payload = new HashMap<>();

		payload.put("username", "jackliddiard");
		payload.put("socialSecurityNumber", "111-111-111");
		payload.put("password", "PaSsw0rd");
		// 01/01/1994
		payload.put("dateOfBirth", 8766);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

		final ResponseEntity<Map> result = restOperations.exchange(endpoint, HttpMethod.POST, entity, Map.class);
		
		
		assertThat(result.getStatusCode(), is(HttpStatus.FORBIDDEN));
		assertThat((String) result.getBody().get("dob"), equalTo("1994-01-01"));
		assertThat((String) result.getBody().get("socialSecurityNumber"), equalTo("111-111-111"));
		assertThat((String) result.getBody().get("errorMessage"), equalTo("user.blacklisted"));
	}
	
	/**
	 * Tests that a completely valid user which shares a similar social security number
	 * and the exact same date of birth is allowed and returns a void/empty body with a 200.
	 */
	@Test
	public void testValidUser() {

		final Map<String, Object> payload = new HashMap<>();

		payload.put("username", "jackliddiard");
		payload.put("socialSecurityNumber", "121-111-111");
		payload.put("password", "PaSsw0rd");
		// 01/01/1994
		payload.put("dateOfBirth", 8766);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

		final ResponseEntity<String> result = restOperations.exchange(endpoint, HttpMethod.POST, entity, String.class);
		
		assertThat(result.getStatusCode(), is(HttpStatus.OK));
		assertThat(result.getBody(), is(IsEmptyString.isEmptyOrNullString()));
	}
}