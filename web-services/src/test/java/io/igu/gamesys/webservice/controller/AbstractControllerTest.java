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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.igu.gamesys.AbstractTest;
import io.igu.gamesys.webservice.config.ControllerContext;

/**
 * An abstract test case for web service testing. This class brings in all of the
 * spring context for controllers and data binding, as well as setsup the {@link MockMvc}
 * class.
 *
 * @author Jack Liddiard
 */
@Configuration
@WebAppConfiguration
@ContextConfiguration(classes={ControllerContext.class})
public class AbstractControllerTest extends AbstractTest {

	@Bean
	public MockMvc mockMvc(final WebApplicationContext wac) {
		return MockMvcBuilders
		.webAppContextSetup(wac)
		.alwaysDo(MockMvcResultHandlers.log())
		.build();
	}
}