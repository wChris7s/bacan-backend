package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.RoleMicroservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class RoleMicroserviceClientAdapter implements RoleMicroservice {

  private final WebClient webClient;

  public RoleMicroserviceClientAdapter(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Void> validateRole(Long roleId) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/{id}/validate")
        .build(roleId))
      .retrieve()
      .bodyToMono(Void.class)
      .doOnError(e -> {
        log.error("An error occurred validating role: {}", e.getMessage());
        throw new RuntimeException("An error occurred validating user.");
      });
  }
}
