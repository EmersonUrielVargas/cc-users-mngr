package com.challengecloud.users.domain.exception;

public class MissingParamRequiredException extends DomainException {
    public MissingParamRequiredException(String message) {
        super(message);
    }
}
