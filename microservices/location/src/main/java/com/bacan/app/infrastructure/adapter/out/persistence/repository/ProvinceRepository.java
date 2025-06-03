package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProvinceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProvinceRepository extends ReactiveCrudRepository<ProvinceEntity, String> {
  Flux<ProvinceEntity> findAllByStateId(String stateId);
}
