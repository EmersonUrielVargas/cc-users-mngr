package com.challengecloud.users.domain.validators;

import com.challengecloud.users.domain.constants.Constants;
import com.challengecloud.users.domain.exception.InvalidFormatParamException;
import com.challengecloud.users.domain.exception.MissingParamRequiredException;

import java.util.Optional;

public class UserValidator {

    private UserValidator() {
        throw new IllegalStateException(Constants.INSTANCE_UTILITY_CLASS);
    }


    public static void validateEmail(String email){
        validateIsNull(email);
        if (!email.matches(Constants.EMAIL_PATTERN)){
            throw new InvalidFormatParamException(Constants.INVALID_EMAIL);
        }
    }

    public static void validateIDNumber(String idNumber){
        validateIsNull(idNumber);
        if (!idNumber.matches(Constants.ID_NUMBER_PATTERN)){
             throw new InvalidFormatParamException(Constants.INVALID_ID_NUMBER);
        }
    }

    public static <T> void validateIsNull(T value){
        Optional.ofNullable(value)
                .filter(valueFiltered -> !(valueFiltered instanceof String stringValue) || !stringValue.isBlank())
                .orElseThrow(() -> new MissingParamRequiredException(Constants.PARAM_REQUIRED_NOT_FOUND));
    }

}
