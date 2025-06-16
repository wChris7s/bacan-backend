package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, String> {
}
