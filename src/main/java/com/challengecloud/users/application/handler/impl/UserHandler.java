package com.challengecloud.users.application.handler.impl;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;
import com.challengecloud.users.application.handler.IUserHandler;
import com.challengecloud.users.application.mapper.IUserRequestMapper;
import com.challengecloud.users.domain.api.IUserServicePort;
import com.challengecloud.users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void createUser(CreateUserRequestDto userDto) {
        User userToCreate = userRequestMapper.toUser(userDto);
        userServicePort.createUser(userToCreate);
    }

    @Override
    public GetUserResponseDto getUserByEmail(String email) {
        return userRequestMapper.toGetUserResponseDto(userServicePort.getUserByEmail(email));
    }
}
