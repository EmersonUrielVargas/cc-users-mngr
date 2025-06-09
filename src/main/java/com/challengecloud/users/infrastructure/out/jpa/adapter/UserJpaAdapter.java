package com.challengecloud.users.infrastructure.out.jpa.adapter;

import com.challengecloud.users.domain.model.User;
import com.challengecloud.users.domain.spi.IUserPersistencePort;
import com.challengecloud.users.infrastructure.out.jpa.entity.UserEntity;
import com.challengecloud.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.challengecloud.users.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        return userEntityMapper.toUser(userRepository.save(userEntityMapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> getUserByEmail(String emailUser) {
        Optional<UserEntity> userEntity =  userRepository.findByEmail(emailUser);
        return userEntity.map(userEntityMapper::toUser);
    }
}
