package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreRepository extends
  ReactiveCrudRepository<StoreEntity, String>,
  ReactiveSortingRepository<StoreEntity, String> {
  @Query("SELECT DISTINCT s.* FROM bacan.store s " +
    "WHERE (:name IS NULL OR s.name ILIKE CONCAT('%', :name, '%'))")
  Flux<StoreEntity> findAllByName(
    @Param("name") String name,
    Pageable pageable);

  @Query("SELECT COUNT(DISTINCT s.id) FROM bacan.store s " +
    "WHERE (:name IS NULL OR s.name ILIKE CONCAT('%', :name, '%'))")
  Mono<Long> countAllByName(@Param("name") String name);
}
