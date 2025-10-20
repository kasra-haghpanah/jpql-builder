package com.tosan.bpms.framework.validator.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by kasra.haghpanah on 22/06/2018.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ComplexBeanValidator.class})
@Documented
public @interface ComplexBeanValid {
    /* ideally there should be just localization key, but for simplicity just message */
    String message() default "Bean is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
