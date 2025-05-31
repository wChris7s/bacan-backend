package com.bacan.app.application.port.out.http;

import reactor.core.publisher.Mono;

import java.util.List;

public interface RolePort {
  Mono<Void> validateRoleIds(List<Long> roleIds);
}
