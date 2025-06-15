package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.RolePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class RolePortClientAdapter implements RolePort {

  private final WebClient webClient;

  public RolePortClientAdapter(WebClient webClient) {
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
        log.error("An error occurred validating roled: {}", e.getMessage());
        throw new RuntimeException("An error occurred validating roles.");
      });
  }
}
