package com.challengecloud.users.domain.exception;

import com.challengecloud.users.domain.constants.Constants;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super(Constants.USER_NO_FOUND);
    }

}
