package mirosimo.car_showroom2.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import mirosimo.car_showroom2.custom_annotations.EnChars1_50;


public class EnChars1_50Validator implements ConstraintValidator<EnChars1_50, String> {
	
	@Override
	public void initialize(EnChars1_50 chars) {}
	
	@Override
	public boolean isValid(String dataField, ConstraintValidatorContext context) {
		return dataField !=null 
				&& dataField.matches("^[.-a-zA-Z0-9\s]+$")
				&& dataField.length() > 0
				&& dataField.length() < 51;
	}
}
