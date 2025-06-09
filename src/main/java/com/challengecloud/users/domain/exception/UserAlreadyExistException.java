package com.challengecloud.users.domain.exception;

import com.challengecloud.users.domain.constants.Constants;

public class UserAlreadyExistException extends DomainException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
    public UserAlreadyExistException() {
        super(Constants.USER_ALREADY_EXIST);
    }

}
