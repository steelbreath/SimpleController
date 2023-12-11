package com.shpp.simplecontroller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IPNValidator.class)
@Documented
public @interface IPN {

    String message() default "IPN should follow the next template: 1234567899.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
