package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.application.port.in.http.ProductUseCase;
import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.domain.models.product.Product;
import com.bacan.app.domain.queries.product.ProductQuery;
import lombok.Builder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Builder
public class ProductFacade {
  private final ProductUseCase productUseCase;

  private final CategoryUseCase categoryUseCase;

  private final StoreUseCase storeUseCase;

  public Flux<Product> getAllProductsWithTheirCategoriesAndStore(ProductQuery productQuery) {
    return productUseCase.getAllProductsByQuery(productQuery)
      .flatMap(this::fillProduct);
  }

  public Mono<Product> getProductWithTheirCategoriesAndStoreById(String productId) {
    return productUseCase.getProductById(productId)
      .flatMap(this::fillProduct);
  }

  private Mono<Product> fillProduct(Product product) {
    return Mono.zip(
      categoryUseCase.getCategoriesByProductId(product.id()).collectList(),
      storeUseCase.findStoreById(product.storeId())
    ).map(tuple -> product
      .withCategories(tuple.getT1())
      .withStore(tuple.getT2())
    );
  }
}
