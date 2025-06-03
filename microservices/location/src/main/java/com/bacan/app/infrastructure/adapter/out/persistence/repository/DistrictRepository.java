package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.DistrictEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DistrictRepository extends ReactiveCrudRepository<DistrictEntity, String> {
  Flux<DistrictEntity> findAllByProvinceIdAndStateId(String provinceId, String stateId);
}
