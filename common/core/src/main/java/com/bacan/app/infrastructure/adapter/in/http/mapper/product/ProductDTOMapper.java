package com.bacan.app.infrastructure.adapter.in.http.mapper.product;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductDTOMapper {
  public static ProductResponseDTO map(Product product) {
    return ProductResponseDTO.builder()
      .id(product.id())
      .name(product.name())
      .price(product.price())
      .stock(product.stock())
      .description(product.description())
      .photoUrl(product.photoUrl())
      .createdAt(product.createdAt())
      .store(
        product.store() == null
          ? null
          : ProductStoreResponseDTO.builder()
          .name(product.store().name())
          .logoUrl(product.store().logoUrl())
          .build()
      )
      .categories(
        product.categories() == null
          ? List.of()
          : product.categories().stream()
          .map(category -> ProductCategoryResponseDTO.builder()
            .name(category.name())
            .build())
          .toList()
      )
      .build();
  }

  public static Product map(CreateProductDTO dto) {
    return Product.builder()
      .name(dto.getName())
      .price(dto.getPrice())
      .stock(dto.getStock())
      .description(dto.getDescription())
      .build();
  }

  public static Product map(UpdateProductDTO dto) {
    return Product.builder()
      .name(dto.getName())
      .price(dto.getPrice())
      .stock(dto.getStock())
      .description(dto.getDescription())
      .build();
  }
}
