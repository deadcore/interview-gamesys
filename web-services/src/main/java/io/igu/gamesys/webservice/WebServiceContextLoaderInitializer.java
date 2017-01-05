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
package io.igu.gamesys.webservice;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import io.igu.gamesys.core.config.CoreApplicationContext;
import io.igu.gamesys.webservice.config.ControllerContext;

/**
 * Bootstraps the web service layer by loading the {@link CoreApplicationContext}
 * then loading in the {@link ControllerContext}.
 *
 * @author Jack Liddiard
 */
public final class WebServiceContextLoaderInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { CoreApplicationContext.class };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ControllerContext.class };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}
	
}