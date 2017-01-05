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
package io.igu.gamesys.webservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Validates that a {@link String} password adhears to the following contract.
 * <p>
 * <ul>
 * <li><strong>MUST</strong> be at least four characters</li>
 * <li><strong>MUST</strong> be at least one upper case character</li>
 * <li><strong>MUST</strong> be at least one number</li>
 * </ul>
 *
 * @author Jack Liddiard
 */
@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotEmpty
public @interface ValidPassword {
	String message() default "{io.igu.gamesys.core.validation.ValidPassword.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
