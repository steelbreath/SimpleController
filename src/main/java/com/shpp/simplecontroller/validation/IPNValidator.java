package com.shpp.simplecontroller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IPNValidator implements ConstraintValidator<IPN, Long> {

    private static final int[] MULTIPLICATION_NUMBER = {-1, 5, 7, 9, 4, 6, 10, 5, 7};

    @Override
    public boolean isValid(Long l, ConstraintValidatorContext constraintValidatorContext) {
        String s = l.toString();
        if (s.matches("\\d{10}")) {
            int controlSum = 0;
            for (int i = 0; i < s.length() - 1; i++) {
                char ch = s.charAt(i);
                controlSum += MULTIPLICATION_NUMBER[i] * Integer.parseInt(String.valueOf(ch));
            }
            if (String.valueOf(s.charAt(s.length() - 1)).equals(String.valueOf(controlSum % 11))) return true;
        }
        return false;
    }
}
