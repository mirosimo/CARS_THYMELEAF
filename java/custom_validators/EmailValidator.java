package mirosimo.car_showroom2.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mirosimo.car_showroom2.custom_annotations.EmailCheck;



public class EmailValidator implements ConstraintValidator<EmailCheck, String> {
	
	@Override
	public void initialize(EmailCheck chars) {}
	
	@Override
	public boolean isValid(String dataField, ConstraintValidatorContext context) {
		return dataField !=null 
				&& dataField.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$");	
	} 
}
