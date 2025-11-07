package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.ProductDatabase;
import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.ProductEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductCategoryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class ProductPostgresAdapter implements ProductDatabase {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    static final ProductEntityMapper productEntityMapper = Mappers.getMapper(ProductEntityMapper.class);

    public ProductPostgresAdapter(ProductRepository productRepository,
                                  ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    /* ============ Product ============ */

    @Override
    public Mono<Product> createProduct(Product product) {
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        return productRepository.save(productEntity)
                .map(productEntityMapper::toModel);
    }

    @Override
    public Flux<Product> findAllProducts(ProductQuery query) {
        // Force nullable values to pass the query condition on repository.
        if (Objects.isNull(query.getCategoryIds()) || query.getCategoryIds().isEmpty()) {
            query.setCategoryIds(null);
        }
        return productRepository.findAllByQuery(query, query.getPageable())
                .map(productEntityMapper::toModel);
    }

    @Override
    public Mono<Product> findProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productEntityMapper::toModel);
    }

    @Override
    public Mono<Long> countProductsByQuery(ProductQuery query) {
        // Force nullable values to pass the query condition on repository.
        if (Objects.isNull(query.getCategoryIds()) || query.getCategoryIds().isEmpty()) {
            query.setCategoryIds(null);
        }
        return productRepository.countAllByQuery(query);
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        return this.createProduct(product);
    }

    /* ============ Product Category ============ */

    @Override
    public Mono<Void> createProductCategory(Long productId, Long categoryId) {
        return productCategoryRepository.save(productId, categoryId);
    }

    @Override
    public Mono<Void> deleteProductCategory(Long productId, Long categoryId) {
        return productCategoryRepository.delete(productId, categoryId);
    }
}
