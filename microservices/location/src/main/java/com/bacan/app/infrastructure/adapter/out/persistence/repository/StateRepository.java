package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.StateEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StateRepository extends ReactiveCrudRepository<StateEntity, String> {
  Flux<StateEntity> findAllByCountryId(Long countryId);
}
