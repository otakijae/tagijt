package com.otakijae.tagit.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNoValidator implements ConstraintValidator<UserNoConstraint, String> {

    @Override
    public void initialize(UserNoConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext cxt) {
        return input != null && input.matches("[0-9](8)") && (input.length() > 8) && (input.length() < 14);
    }

}
