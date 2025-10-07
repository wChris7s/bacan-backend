package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.UserClient;
import com.bacan.app.infrastructure.adapter.in.http._const.ApiPathConstant;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClientAdapter implements UserClient {

  private final WebClient webClient;

  public UserClientAdapter(
    @NonNull
    @Value("${application.microservice.url.ms-user}") String msUserUrl) {
    this.webClient = WebClient.create(msUserUrl + ApiPathConstant.USER_URL);
  }

  @Override
  public Mono<UserDTO> getUserByDocumentId(String documentId) {
    return webClient.get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/{documentId}")
          .build(documentId))
      .retrieve()
      .bodyToMono(UserDTO.class);
  }
}
