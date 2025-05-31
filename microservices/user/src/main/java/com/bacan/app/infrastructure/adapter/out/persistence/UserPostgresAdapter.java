package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.model.user.User;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserPostgresAdapter implements UserDatabasePort {
  private final UserRepository userRepository;

  public UserPostgresAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Mono<User> createUser(User user) {
    UserEntity userEntity = UserEntityMapper.mapToEntity(user);
    return this.userRepository.save(userEntity)
        .map(UserEntityMapper::mapToModel);
  }

  @Override
  public Flux<User> findAllUsers() {
    return null;
  }
}
