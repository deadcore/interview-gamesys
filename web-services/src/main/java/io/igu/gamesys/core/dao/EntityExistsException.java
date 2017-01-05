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
package io.igu.gamesys.core.dao;

/**
 *
 *
 * @author Jack Liddiard
 */
public final class EntityExistsException extends RuntimeException {
	
	/**
	 * No Markup
	 */
	private static final long serialVersionUID = -4474970250905218999L;

	public EntityExistsException(final String message) {
		super(message);
	}
	
}
