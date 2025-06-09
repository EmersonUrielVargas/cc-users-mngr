package com.challengecloud.users.application.handler;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;

public interface IUserHandler {

    void createUser(CreateUserRequestDto userDto);
    GetUserResponseDto getUserByEmail(String email);


}
