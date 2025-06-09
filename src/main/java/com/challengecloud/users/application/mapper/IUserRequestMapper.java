package com.challengecloud.users.application.mapper;

import com.challengecloud.users.application.dto.request.CreateUserRequestDto;
import com.challengecloud.users.application.dto.response.GetUserResponseDto;
import com.challengecloud.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    User toUser(CreateUserRequestDto createUserRequestDto);

    GetUserResponseDto toGetUserResponseDto(User user);

}
