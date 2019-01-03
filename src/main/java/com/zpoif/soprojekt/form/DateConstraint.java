package com.zpoif.soprojekt.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@interface DateConstraint {
    String message() default "Invalid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}