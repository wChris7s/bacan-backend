package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.RolePort;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class RolePortClientAdapter implements RolePort {

  private final WebClient webClient;

  public RolePortClientAdapter(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Void> validateRoleIds(List<Long> roleIds) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/validate")
            .queryParam("roleIds", roleIds.toArray())
            .build())
        .retrieve()
        .bodyToMono(Void.class)
        .doOnError(e -> System.err.println("Error validating roles: " + e.getMessage()));
  }
}
