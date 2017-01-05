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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.igu.gamesys.core.dao.UserDao;
import io.igu.gamesys.core.domain.User;

/**
 * A test suit surrounding {@link RegistrationController}.
 *
 * @author Jack Liddiard
 */
public final class RegistrationControllerTest extends AbstractControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Tests that when an empty payload is submitted to the server reponds
	 * @throws Exception
	 */
	@Test
	public void testEmptyPayloadReturnsBadRequest() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest());
	}
	
	/**
	 * Tests that when an empty payload is submitted to the server reponds
	 * with the correct error message
	 * @throws Exception
	 */
	@Test
	public void testEmptyPayloadErrorMessage() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(jsonPath("$.errorMessage", equalTo("object.fielderrors")));
	}
	
	/**
	 * Testa an empty password is not allowed
	 * @throws Exception
	 */
	@Test
	public void testEmptyUsernameReturnsEmptyUsernameFieldError() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("username.NotEmpty")));
	}
	
	/**
	 * Testa an empty password is not allowed
	 * @throws Exception
	 */
	@Test
	public void testEmptyPasswordReturnsEmptyPasswordFieldError() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("password.NotEmpty")));
	}
	
	/**
	 * Tests that an empty date of birth is not allowed and validation error is shown
	 * @throws Exception
	 */
	@Test
	public void testNullDateOfBirthReturnsEmptyDateOfBirthFieldError() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("dateOfBirth.NotNull")));
	}
	
	/**
	 * Tests that an empty social security number is not allowed and validation error is shown
	 * @throws Exception
	 */
	@Test
	public void testEmptySocialSecurityNumber() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("socialSecurityNumber.NotEmpty")));
	}
	
	/**
	 * Tests that a password is allowed to be only lower case
	 * @throws Exception
	 */
	@Test
	public void testUsernameAllowsLowerCaseAlphabetricCharacters() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"abcdefghijklmnopqrstuvwxyz\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", not(contains("username.NotEmpty", "username.ValidUsername"))));
	}
	
	/**
	 * Tests that a username is allowed to be just upper case charaicters
	 * @throws Exception
	 */
	@Test
	public void testUsernameAllowsUpperCaseAlphabetricCharacters() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", not(contains("username.NotEmpty", "username.ValidUsername"))));
	}
	
	/**
	 * Tests that a username is allowed to be numbers
	 * @throws Exception
	 */
	@Test
	public void testUsernameAllowsNumbers() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"123456789\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", not(contains("username.NotEmpty", "username.ValidUsername"))));
	}
	
	/**
	 * Tests that a username is not allowed special characters
	 * @throws Exception
	 */
	@Test
	public void testUsernameDoesNotAllowSpecialCharacter() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"Hello ?\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("username.ValidUsername")));
	}
	
	/**
	 * Tests that a username is not allowed spaces
	 * @throws Exception
	 */
	@Test
	public void testUsernameDoesNotAllowSpaceCharacter() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"My Name\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("username.ValidUsername")));
	}
	
	/**
	 * Tests that if a password is less than 4 characters it fails validation
	 * @throws Exception
	 */
	@Test
	public void testPasswordMustBeAtLeasFourCharacters() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"password\": \"Ab1\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("password.ValidPassword")));
	}
	
	/**
	 * Tests that if a password does not have an number value it fails validation
	 * @throws Exception
	 */
	@Test
	public void testPasswordMustHaveANumber() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"password\": \"Abcd\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("password.ValidPassword")));
	}
	
	/**
	 * Tests that if a password does not have an upper case value it fails validation
	 * @throws Exception
	 */
	@Test
	public void testPasswordMustHaveAnUpperCaseCharacter() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"password\": \"1bcd\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("password.ValidPassword")));
	}
	
	/**
	 * Tests that if a password does not have a lower case value it fails validation
	 * @throws Exception
	 */
	@Test
	public void testPasswordMustHaveALowerCaseCharacter() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"password\": \"ABC1\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", hasItem("password.ValidPassword")));
	}
	
	/**
	 * Tests that a valid password does not cause field errors. Positive test case
	 * @throws Exception
	 */
	@Test
	public void testAValidPassword() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"password\": \"AbC1\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", not(contains("password.ValidPassword"))));
	}
	
	/**
	 * Tests a valid date of birth. In this instance {@code 01/01/1994 00:00:00} is used.
	 * @throws Exception
	 */
	@Test
	public void testAValidDateOfBirth() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"dateOfBirth\": \"757382400000\"}")
		)
		.andExpect(jsonPath("$.fieldErrors", not(contains("dateOfBirth.NotNull"))));
	}
	
	/**
	 * Tests a valid date of birth. In this instance {@code 01/01/1994} is used.
	 * @throws Exception
	 */
	@Test
	public void testAValidUserRegistration() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard1\", \"password\": \"Test1\", \"socialSecurityNumber\": \"201-052-445\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(userDao.findByDateOfBirthAndSocialSecurityNumber(LocalDate.of(1994, 1, 1), "201-052-445").isPresent(), is(true));
	}
	
	/**
	 * Tests that when a blacklisted user is added the error message is 'user.blacklisted'
	 * @throws Exception
	 */
	@Test
	public void testBlacklistedUserErrorMessage() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard1\", \"password\": \"Test1\", \"socialSecurityNumber\": \"111-111-111\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(jsonPath("$.errorMessage", is(equalTo("user.blacklisted"))))
		.andReturn();
	}
	
	
	/**
	 * Tests that when a blacklisted user is added the status is forbiden (403).
	 * @throws Exception
	 */
	@Test
	public void testBlacklistedUserReturnsParamatersThatMakeupBlacklist() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard1\", \"password\": \"Test1\", \"socialSecurityNumber\": \"111-111-111\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(jsonPath("$.dob", is(equalTo("1994-01-01"))))
		.andExpect(jsonPath("$.socialSecurityNumber", is(equalTo("111-111-111"))))
		.andReturn();
	}
	
	/**
	 * Tests that when a blacklisted user is added they are not persited to the repository layer
	 * @throws Exception
	 */
	@Test
	public void testBlacklistedUserIsNotPersited() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard1\", \"password\": \"Test1\", \"socialSecurityNumber\": \"111-111-111\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(status().isForbidden())
		.andReturn();
		
		assertThat(userDao.findByDateOfBirthAndSocialSecurityNumber(LocalDate.of(1994, 1, 1), "111-111-111").isPresent(), is(false));
	}
	
	/**
	 * Tests that when a blacklisted user is added the status is forbidden (403).
	 * @throws Exception
	 */
	@Test
	public void testBlacklistedUserReturnCodeIsForbiden() throws Exception {
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard1\", \"password\": \"Test1\", \"socialSecurityNumber\": \"111-111-111\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(status().isForbidden())
		.andReturn();
	}
	
	/**
	 * Tests that when duplicate users are added the status code is conflict (409)
	 * @throws Exception
	 */
	@Test
	public void testDuplicateUserThrowsConflict() throws Exception {
		userDao.save(new User("JackLiddiard", "Test1", LocalDate.ofEpochDay(8766), "201-052-445"));
		
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard\", \"password\": \"Test1\", \"socialSecurityNumber\": \"201-052-445\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(status().isConflict())
		.andReturn();
	}
	
	
	/**
	 * Tests that when duplicate users are added the error code is duplicate username
	 * @throws Exception
	 */
	@Test
	public void testDuplicateUserReturnsCorrectErrorMessage() throws Exception {
		userDao.save(new User("JackLiddiard", "Test1", LocalDate.ofEpochDay(8766), "201-052-445"));
		
		mockMvc
		.perform(post(RegistrationController.REQUEST_MAPPING)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"username\": \"JackLiddiard\", \"password\": \"Test1\", \"socialSecurityNumber\": \"201-052-445\",  \"dateOfBirth\": \"8766\"}")
		)
		.andExpect(jsonPath("$.errorMessage", equalTo("user.duplicateUsername")))
		.andReturn();
	}
}