package com.challengecloud.users.domain.api;

import com.challengecloud.users.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
    User getUserByEmail(String emailUser);
}
