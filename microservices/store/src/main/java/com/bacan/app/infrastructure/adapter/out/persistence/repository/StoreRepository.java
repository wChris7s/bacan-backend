package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StoreRepository extends ReactiveCrudRepository<StoreEntity, Long> {
}
