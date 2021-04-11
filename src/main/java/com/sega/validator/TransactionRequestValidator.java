package com.sega.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransactionRequestValidation.class)
public @interface TransactionRequestValidator {

    String message() default "Invalid Request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}