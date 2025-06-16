package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {
  Flux<RoleEntity> findAllByIsPublic(boolean isPublic);
}
