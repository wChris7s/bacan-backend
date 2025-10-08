package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.UserClient;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http._const.ApiPathConstant;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.user.UserDTOMapper;
import lombok.NonNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClientAdapter implements UserClient {

  private final WebClient webClient;

  static final UserDTOMapper userDtoMapper = Mappers.getMapper(UserDTOMapper.class);

  public UserClientAdapter(
    @NonNull
    @Value("${application.microservice.url.ms-user}") String msUserUrl) {
    this.webClient = WebClient.create(msUserUrl + ApiPathConstant.USER_URL);
  }

  @Override
  public Mono<User> getUserByDocumentId(String documentId) {
    return webClient.get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/doc/{documentId}")
          .build(documentId))
      .retrieve()
      .bodyToMono(UserDTO.class)
      .map(userDtoMapper::map);
  }

  @Override
  public Mono<User> getUserById(Long userId) {
    return webClient.get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/{documentId}")
          .build(userId))
      .retrieve()
      .bodyToMono(UserDTO.class)
      .map(userDtoMapper::map);
  }
}
