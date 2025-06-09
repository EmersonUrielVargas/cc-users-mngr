package com.challengecloud.users.infrastructure.out.jpa.mapper;

import com.challengecloud.users.domain.model.User;
import com.challengecloud.users.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    @Mapping(source = "lastName", target = "surname")
    UserEntity toUserEntity(User user);

    @InheritInverseConfiguration
    User toUser(UserEntity userEntity);
}
