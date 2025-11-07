package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.domain.queries.product.ProductQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends
  ReactiveCrudRepository<ProductEntity, Long>,
  ReactiveSortingRepository<ProductEntity, Long> {

  @Query("""
    SELECT p FROM store.product p
    INNER JOIN store.product_category pc on p.id = pc.product_id
    WHERE (
        (:#{[0].name} IS NULL OR lower(p.name) LIKE concat('%', lower(:#{[0].name}), '%')) AND
        (:#{[0].storeId} IS NULL OR p.store_id = :#{[0].storeId}) AND
        (:#{[0].categoryIds} IS NULL OR pc.category_id IN :#{[0].categoryIds})
    )
    """)
  Flux<ProductEntity> findAllByQuery(ProductQuery query, Pageable pageable);

  @Query("""
    SELECT count(p.id) FROM store.product p
    INNER JOIN store.product_category pc on p.id = pc.product_id
    WHERE (
        (:#{[0].name} IS NULL OR lower(p.name) LIKE concat('%', lower(:#{[0].name}), '%')) AND
        (:#{[0].storeId} IS NULL OR p.store_id = :#{[0].storeId}) AND
        (:#{[0].categoryIds} IS NULL OR pc.category_id IN :#{[0].categoryIds})
    )
    """)
  Mono<Long> countAllByQuery(ProductQuery query);
}
