package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreRepository extends ReactiveCrudRepository<StoreEntity, Long> {
  @Query("""
    SELECT s FROM store.store s
    WHERE (
        (:#{[0]}.name is NULL OR lower(s.name) LIKE concat('%', lower(:#{[0]}.name), '%')) AND
        s.enabled IS TRUE
    )
    """)
  Flux<StoreEntity> findAllByQuery(StoreQuery query, Pageable pageable);


  @Query("""
    SELECT count(s.id) FROM store.store s
    WHERE (
        (:#{[0]}.name is NULL OR lower(s.name) LIKE concat('%', lower(:#{[0]}.name), '%')) AND
        s.enabled IS TRUE
    )
    """)
  Mono<Long> countAllByQuery(StoreQuery query);
}
