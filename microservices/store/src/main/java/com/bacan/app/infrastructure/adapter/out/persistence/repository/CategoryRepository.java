package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryEntity, Long>, ReactiveSortingRepository<CategoryEntity, Long> {
  Flux<CategoryEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

  Mono<Long> countAllByNameContainingIgnoreCase(String name);

  @Query("SELECT c FROM store.category c " +
    "INNER JOIN store.product_category pc ON c.id = pc.category_id " +
    "WHERE pc.product_id = :productId")
  Flux<CategoryEntity> findAllByProductId(@Param("productId") Long productId);
}
