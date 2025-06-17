package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends
  ReactiveCrudRepository<ProductEntity, UUID>,
  ReactiveSortingRepository<ProductEntity, UUID> {

  @Query("SELECT DISTINCT p.* FROM bacan.product p " +
    "INNER JOIN bacan.product_category pc on p.id = pc.product_id " +
    "WHERE " +
    "    (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%')) AND " +
    "    (:storeId IS NULL OR p.store_id = CAST(:storeId AS UUID)) AND " +
    "    (pc.category_id IN (:categoryIds))")
  Flux<ProductEntity> findAllByNameOrCategories(
    @Param("name") String name,
    @Param("storeId") String storeId,
    @Param("categoryIds") List<Long> categoryIds,
    Pageable pageable);

  @Query("SELECT DISTINCT p.* FROM bacan.product p " +
    "INNER JOIN bacan.product_category pc on p.id = pc.product_id " +
    "WHERE " +
    "    (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%')) AND " +
    "    (:storeId IS NULL OR p.store_id = CAST(:storeId AS UUID))")
  Flux<ProductEntity> findAllByName(
    @Param("name") String name,
    @Param("storeId") String storeId,
    Pageable pageable);

  @Query("SELECT COUNT(DISTINCT p.id) FROM bacan.product p " +
    "INNER JOIN bacan.product_category pc on p.id = pc.product_id " +
    "WHERE " +
    "    (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%')) AND " +
    "    (:storeId IS NULL OR p.store_id = CAST(:storeId AS UUID)) AND " +
    "    (pc.category_id IN (:categoryIds))")
  Mono<Long> countAllByNameOrCategories(
    @Param("name") String name,
    @Param("storeId") String storeId,
    @Param("categoryIds") List<Long> categoryIds);

  @Query("SELECT COUNT(DISTINCT p.id) FROM bacan.product p " +
    "INNER JOIN bacan.product_category pc on p.id = pc.product_id " +
    "WHERE " +
    "    (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%')) AND " +
    "    (:storeId IS NULL OR p.store_id = CAST(:storeId AS UUID))")
  Mono<Long> countAllByName(
    @Param("name") String name,
    @Param("storeId") String storeId
  );
}
