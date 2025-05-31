package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {
  Mono<Long> countAllByIdIsIn(Collection<Long> ids);
}
