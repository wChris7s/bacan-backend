package com.bacan.app.infrastructure.adapter.in.http.mapper.product;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductStoreDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductDTOMapper {
  public static ProductResponseDTO mapToDto(Product product) {
    return ProductResponseDTO.builder()
      .id(product.id())
      .name(product.name())
      .price(product.price())
      .stock(product.stock())
      .description(product.description())
      .photo(product.photo())
      .createdAt(product.createdAt())
      .store(ProductStoreDTO.builder()
        .name(product.store().name())
        .logo(product.store().logo())
        .build())
      .categories(
        product.categories().stream()
          .map(category -> ProductCategoryDTO.builder()
            .name(category.name())
            .build())
          .toList())
      .build();
  }
}
