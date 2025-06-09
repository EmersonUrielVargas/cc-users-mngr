package com.challengecloud.users.infrastructure.configuration;

import com.challengecloud.users.domain.api.IUserServicePort;
import com.challengecloud.users.domain.spi.IUserPersistencePort;
import com.challengecloud.users.domain.usecase.UserUseCase;
import com.challengecloud.users.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.challengecloud.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.challengecloud.users.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }


    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }
}