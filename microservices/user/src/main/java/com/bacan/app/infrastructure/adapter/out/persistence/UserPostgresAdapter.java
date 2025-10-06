package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserPostgresAdapter implements UserDatabasePort {

  static final UserEntityMapper userEntityMapper = Mappers.getMapper(UserEntityMapper.class);

  private final UserRepository userRepository;

  @Override
  public Mono<User> createUser(User user) {
    UserEntity userEntity = userEntityMapper.mapToEntity(user);
    return this.userRepository.save(userEntity)
      .map(userEntityMapper::mapToModel);
  }

  @Override
  public Mono<User> updateUser(User user) {
    return createUser(user);
  }

  @Override
  public Flux<User> findAll() {
    return userRepository.findAll()
      .map(userEntityMapper::mapToModel);
  }

  @Override
  public Mono<User> findUserById(Long userId) {
    return userRepository.findById(userId)
      .map(userEntityMapper::mapToModel);
  }

  @Override
  public Mono<User> findUserByDocumentId(String documentId) {
    return userRepository.findByDocumentId(documentId)
      .map(userEntityMapper::mapToModel);
  }
}
