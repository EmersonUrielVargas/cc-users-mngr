package com.challengecloud.users.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserBasicDto {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String idNumber;
    @NotNull
    private String email;
}
