package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StoreRepository extends ReactiveCrudRepository<StoreEntity, UUID> {

  @Query("""
      SELECT s.*
      FROM bacan.store s
      WHERE (:name IS NULL OR s.name ILIKE CONCAT('%', :name, '%'))
      ORDER BY s.created_at DESC, s.id DESC
      LIMIT :#{#pageable.pageSize}
      OFFSET :#{#pageable.offset}
      """)
  Flux<StoreEntity> findAllByName(@Param("name") String name, Pageable pageable);

  @Query("""
      SELECT COUNT(*)
      FROM bacan.store s
      WHERE (:name IS NULL OR s.name ILIKE CONCAT('%', :name, '%'))
      """)
  Mono<Long> countAllByName(@Param("name") String name);
}
