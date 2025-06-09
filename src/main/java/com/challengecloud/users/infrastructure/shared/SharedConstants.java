package com.challengecloud.users.infrastructure.shared;

import static com.challengecloud.users.domain.constants.Constants.INSTANCE_UTILITY_CLASS;

public class SharedConstants {

    private SharedConstants() {
        throw new IllegalStateException(INSTANCE_UTILITY_CLASS);
    }

    public static final String STATUS_CODE_CREATED= "201";
    public static final String STATUS_CODE_CONFLICT= "409";
    public static final String STATUS_CODE_OK= "200";
    public static final String STATUS_CODE_NOT_FOUND= "404";

    public static final String MEDIA_TYPE_JSON= "application/json";

    public static final String SUMMARY_SAVE_USER = "Create a new user in the application";
    public static final String RESPONSE_CREATED_SAVE_USER = "user created successful";
    public static final String RESPONSE_CONFLICT_SAVE_USER = "Information provided are invalid or incorrect format";

    public static final String SUMMARY_GET_USER_BY_EMAIL = "Find a user by email";
    public static final String RESPONSE_OK_GET_USER = "User Found and return user data";
    public static final String RESPONSE_CONFLICT_GET_USER = "Input parameters are invalid or incorrect format";
    public static final String RESPONSE_NOT_FOUND_GET_USER = "Not found users match with the input parameters";

    /*Params*/
    public static final String PARAM_NAME_EMAIL = "email";

    public static final String DEFAULT_PARAM_EXCEPTION_RESPONSE = "message";

}
