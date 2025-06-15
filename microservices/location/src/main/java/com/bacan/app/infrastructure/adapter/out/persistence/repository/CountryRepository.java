package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.CountryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CountryRepository extends ReactiveCrudRepository<CountryEntity, Long> {
  Flux<CountryEntity> findAllByNameLike(String name);
}
