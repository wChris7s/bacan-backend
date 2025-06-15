package com.bacan.app.application.port.out.http;

import reactor.core.publisher.Mono;

public interface RolePort {
  Mono<Void> validateRole(Long roleId);
}
