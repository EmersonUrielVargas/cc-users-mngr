package com.challengecloud.users.domain.usecase;

import com.challengecloud.users.domain.api.IUserServicePort;
import com.challengecloud.users.domain.exception.UserAlreadyExistException;
import com.challengecloud.users.domain.exception.UserNotFoundException;
import com.challengecloud.users.domain.model.User;
import com.challengecloud.users.domain.spi.IUserPersistencePort;
import com.challengecloud.users.domain.validators.UserValidator;

public class UserUseCase implements IUserServicePort{

    private final IUserPersistencePort userPersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void createUser(User userToCreate) {
        UserValidator.validateIsNull(userToCreate.getName());
        UserValidator.validateIsNull(userToCreate.getLastName());
        UserValidator.validateEmail(userToCreate.getEmail());
        UserValidator.validateIDNumber(userToCreate.getIdNumber());
        userPersistencePort.getUserByEmail(userToCreate.getEmail())
                .ifPresent(value-> {
                    throw new UserAlreadyExistException();
                });
        userPersistencePort.saveUser(userToCreate);
    }

    @Override
    public User getUserByEmail(String emailUser) {
        UserValidator.validateEmail(emailUser);
        return userPersistencePort.getUserByEmail(emailUser)
                .orElseThrow(UserNotFoundException::new);
    }

}
