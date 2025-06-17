package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.ProductDatabase;
import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.models.product.ProductCategory;
import com.bacan.app.domain.queries.product.ProductQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductCategoryEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.ProductCategoryEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.ProductEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductCategoryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ProductPostgresAdapter implements ProductDatabase {
  private final ProductRepository productRepository;

  private final ProductCategoryRepository productCategoryRepository;

  //private final DatabaseClient databaseClient;

  public ProductPostgresAdapter(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
    this.productRepository = productRepository;
    this.productCategoryRepository = productCategoryRepository;
  }

  @Override
  public Mono<Product> createProduct(Product product) {
    ProductEntity productEntity = ProductEntityMapper.mapToEntity(product);
    return productRepository.save(productEntity)
      .map(ProductEntityMapper::mapToModel);
  }

  @Override
  public Flux<Product> findAllProducts(ProductQuery query) {
    if (query.getCategoryIds().isEmpty()) {
      return productRepository.findAllByName(
          query.getName(),
          query.getStoreId(),
          query.getPageable())
        .map(ProductEntityMapper::mapToModel);
    }
    return productRepository.findAllByNameOrCategories(
        query.getName(),
        query.getStoreId(),
        query.getCategoryIds(),
        query.getPageable())
      .map(ProductEntityMapper::mapToModel);
  }

  @Override
  public Mono<Product> findProductById(UUID productId) {
    return productRepository.findById(productId)
      .map(ProductEntityMapper::mapToModel);
  }

  @Override
  public Mono<Long> countProductsByQuery(ProductQuery query) {
    if (query.getCategoryIds().isEmpty()) {
      return productRepository.countAllByName(
        query.getName(),
        query.getStoreId());
    }
    return productRepository.countAllByNameOrCategories(
      query.getName(),
      query.getStoreId(),
      query.getCategoryIds());
  }

  @Override
  public Mono<Void> createProductCategory(ProductCategory productCategory) {
    ProductCategoryEntity productCategoryEntity = ProductCategoryEntityMapper.mapToEntity(productCategory);
    return productCategoryRepository.save(productCategoryEntity);
  }
}
