package mirosimo.car_showroom2.custom_annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import mirosimo.car_showroom2.custom_validators.EnChars1_50Validator;


@Documented
@Constraint(validatedBy = EnChars1_50Validator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnChars1_50 {
	String message() default "Chars without diacritic - length 1-50";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
