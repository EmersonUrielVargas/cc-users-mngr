package com.challengecloud.users.domain;

import com.challengecloud.users.domain.model.User;
import com.challengecloud.users.shared.DataConstants;

public class DataDomainFactory {

    public static User createUser(){
        return User.builder()
                .id(DataConstants.DEFAULT_USER_ID)
                .email(DataConstants.DEFAULT_USER_EMAIL)
                .idNumber(DataConstants.DEFAULT_USER_ID_NUMBER)
                .name(DataConstants.DEFAULT_USER_NAME)
                .lastName(DataConstants.DEFAULT_USER_SURNAME)
                .build();
    }

}
