package com.challengecloud.users.domain.spi;

import com.challengecloud.users.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User saveUser(User user);

    Optional<User> getUserByEmail(String emailUser);
}
