package mirosimo.car_showroom2.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mirosimo.car_showroom2.custom_annotations.PasswCheck;

public class PasswValidator implements ConstraintValidator<PasswCheck, String> {
	
	@Override
	public void initialize(PasswCheck chars) {}
	
	@Override
	public boolean isValid(String dataField, ConstraintValidatorContext context) {
		return dataField !=null;
			// ToDo - Password checking
				//&& dataField.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$");	
	} 
}


