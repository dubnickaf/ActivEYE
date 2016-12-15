package cz.muni.fi.pa165.activeye.annotations;

import cz.muni.fi.pa165.activeye.validator.PastValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by spriadka on 12/15/16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
@Documented
public @interface Past {
    String message() default "cz.muni.fi.pa165.jsr310.validator.Past.message";
    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};
}
