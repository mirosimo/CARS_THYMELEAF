package mirosimo.car_showroom2.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mirosimo.car_showroom2.custom_annotations.CzechChars1_50;


public class CzechChars1_50Validator implements ConstraintValidator<CzechChars1_50, String>{
	
	@Override
	public void initialize(CzechChars1_50 czechChars) {}
	
	@Override
	public boolean isValid(String dataField, ConstraintValidatorContext context) {
		return dataField !=null 
				&& dataField.matches("^[-ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s\\.]+$")
				&& dataField.length() > 0
				&& dataField.length() < 51;
	}

}
