package mirosimo.car_showroom2.custom_annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import mirosimo.car_showroom2.custom_validators.PasswValidator;

@Documented
@Constraint(validatedBy = PasswValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswCheck {
	String message() default "Password must conatain letters and at least two numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
