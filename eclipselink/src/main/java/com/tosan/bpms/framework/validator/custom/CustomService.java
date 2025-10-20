package com.tosan.bpms.framework.validator.custom;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

/**
 * Created by kasra.haghpanah on 22/06/2018.
 */
@Singleton
public class CustomService {

    public String getRequiredValue() {
        return "perfect";
    }

    /* @Valid will trigger bean validation with includes entire bean validation. */
    @ValidateOnExecution
    public void doAction(@Valid ComplexBean bean) {
    }
}
