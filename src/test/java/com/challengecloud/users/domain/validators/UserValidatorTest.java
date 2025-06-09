package com.challengecloud.users.domain.validators;

import com.challengecloud.users.domain.DataDomainFactory;
import com.challengecloud.users.domain.constants.Constants;
import com.challengecloud.users.domain.exception.InvalidFormatParamException;
import com.challengecloud.users.domain.exception.MissingParamRequiredException;
import com.challengecloud.users.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class UserValidatorTest {

    @Test
    void privateConstructor_shouldThrowIllegalStateException() throws Exception {
        Constructor<UserValidator> constructor = UserValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrownException = assertThrows(InvocationTargetException.class,
                constructor::newInstance
        );

        Throwable cause = thrownException.getCause();
        assertNotNull(cause);
        assertInstanceOf(IllegalStateException.class, cause);
        assertEquals(Constants.INSTANCE_UTILITY_CLASS, cause.getMessage());
    }

    @Test
    void shouldValidateCorrectEmail(){
        User user = DataDomainFactory.createUser();
        Assertions.assertDoesNotThrow(()-> UserValidator.validateEmail(user.getEmail()));
    }


    @Test
    void shouldValidateCorrectIdNumber(){
        User user = DataDomainFactory.createUser();
        Assertions.assertDoesNotThrow(()-> UserValidator.validateIDNumber(user.getIdNumber()));
    }

    @Test
    void shouldValidateWrongEmail(){
        String emailFail = "test@.com";
        InvalidFormatParamException exception = assertThrows(InvalidFormatParamException.class, ()->{
            UserValidator.validateEmail(emailFail);
        });
        assertEquals(Constants.INVALID_EMAIL, exception.getMessage());
    }

    @Test
    void shouldValidateWrongIdNumber(){
        String idNumberInvalid = "1M234T4234";
        InvalidFormatParamException exception = assertThrows(InvalidFormatParamException.class, ()->{
            UserValidator.validateIDNumber(idNumberInvalid);
        });
        assertEquals(Constants.INVALID_ID_NUMBER, exception.getMessage());
    }

    @Test
    void shouldValidateMissingIdNumber(){
        MissingParamRequiredException exception = assertThrows(MissingParamRequiredException.class, ()->{
            UserValidator.validateIDNumber(null);
        });
        assertEquals(Constants.PARAM_REQUIRED_NOT_FOUND, exception.getMessage());
    }

    @Test
    void shouldValidateObjectIsNull(){
        MissingParamRequiredException exception = assertThrows(MissingParamRequiredException.class, ()->{
            UserValidator.validateIsNull(null);
        });
        assertEquals(Constants.PARAM_REQUIRED_NOT_FOUND, exception.getMessage());
    }

    @Test
    void shouldValidateStringEmpty(){
        MissingParamRequiredException exception = assertThrows(MissingParamRequiredException.class, ()->{
            UserValidator.validateIsNull("");
        });
        assertEquals(Constants.PARAM_REQUIRED_NOT_FOUND, exception.getMessage());
    }

    @Test
    void shouldValidateObjectIsNotNull(){
        Assertions.assertDoesNotThrow(()-> UserValidator.validateIsNull(12));
    }


}
