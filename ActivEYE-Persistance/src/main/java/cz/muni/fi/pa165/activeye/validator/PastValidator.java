package cz.muni.fi.pa165.activeye.validator;

import cz.muni.fi.pa165.activeye.annotations.Past;

import java.time.temporal.Temporal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Created by spriadka on 12/15/16.
 */
public class PastValidator implements ConstraintValidator<Past,Temporal> {
    @Override
    public void initialize(Past past) {

    }

    @Override
    public boolean isValid(Temporal value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null){
            return true;
        }
        LocalDate ld = LocalDate.from(value);
        return ld.isBefore(LocalDate.now());
    }
}
