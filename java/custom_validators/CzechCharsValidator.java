package mirosimo.car_showroom2.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mirosimo.car_showroom2.custom_annotations.CzechChars;

public class CzechCharsValidator implements ConstraintValidator<CzechChars, String>{
	
	@Override
	public void initialize(CzechChars czechChars) {}
	
	@Override
	public boolean isValid(String dataField, ConstraintValidatorContext context) {
		return dataField !=null && dataField.matches("^[.-ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]+$");
	}

}
