package com.bacan.app.application.port.out.http;

import reactor.core.publisher.Mono;

public interface RoleMicroservice {
  Mono<Void> validateRole(Long roleId);
}
