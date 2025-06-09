package com.challengecloud.users.application.handler.impl;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;
import com.challengecloud.users.shared.DataConstants;

public class DataApplicationFactory {

    public static CreateUserRequestDto buildCreateUserRequestDto(){
        CreateUserRequestDto userBuilder = new CreateUserRequestDto();
        userBuilder.setName(DataConstants.DEFAULT_USER_NAME);
        userBuilder.setLastName(DataConstants.DEFAULT_USER_SURNAME);
        userBuilder.setIdNumber(DataConstants.DEFAULT_USER_ID_NUMBER);
        userBuilder.setEmail(DataConstants.DEFAULT_USER_EMAIL);
        return userBuilder;
    }

    public static GetUserResponseDto buildGetUserResponseDto(){
        GetUserResponseDto userBuilder = new GetUserResponseDto();
        userBuilder.setName(DataConstants.DEFAULT_USER_NAME);
        userBuilder.setLastName(DataConstants.DEFAULT_USER_SURNAME);
        userBuilder.setIdNumber(DataConstants.DEFAULT_USER_ID_NUMBER);
        userBuilder.setEmail(DataConstants.DEFAULT_USER_EMAIL);
        return userBuilder;
    }


}
