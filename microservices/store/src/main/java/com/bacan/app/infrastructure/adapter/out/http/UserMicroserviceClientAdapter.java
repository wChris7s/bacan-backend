package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.UserMicroservice;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.user.UserDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class UserMicroserviceClientAdapter implements UserMicroservice {
  private final WebClient webClient;

  public UserMicroserviceClientAdapter(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<User> getUserById(String userId) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/{id}")
        .build(userId))
      .retrieve()
      .bodyToMono(UserDTO.class)
      .map(UserDTOMapper::mapToModel)
      .onErrorResume(e -> {
        log.error("An error occurred getting user: {}", e.getMessage());
        throw new RuntimeException("An error occurred getting user.");
      });
  }
}
