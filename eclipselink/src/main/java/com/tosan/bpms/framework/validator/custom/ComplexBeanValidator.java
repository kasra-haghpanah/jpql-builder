package com.tosan.bpms.framework.validator.custom;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by kasra.haghpanah on 22/06/2018.
 */
public class ComplexBeanValidator implements ConstraintValidator<ComplexBeanValid, ComplexBean> {

    @Inject
    private CustomService customService;

    @Override
    public void initialize(ComplexBeanValid constraintAnnotation) {
        /* if annotation contains addition parameter it must be parsed here.. skipping for simplicity.
          NOTE: in such simple case we can make validator singleton, because of no internal state */
    }

    @Override
    public boolean isValid(ComplexBean value, ConstraintValidatorContext context) {
        /* common convention is to treat null values as valid and explicitly check them with @NotNull */
        return value == null || customService.getRequiredValue().equals(value.getUser());
    }
}