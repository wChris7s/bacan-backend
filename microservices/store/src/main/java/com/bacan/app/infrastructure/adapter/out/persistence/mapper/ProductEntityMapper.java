package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductEntityMapper {
  public static ProductEntity mapToEntity(Product product) {
    return ProductEntity.builder()
      .id(product.id())
      .storeId(product.storeId())
      .name(product.name())
      .price(product.price())
      .stock(product.stock())
      .description(product.description())
      .photo(product.photo())
      .createdAt(product.createdAt())
      .updatedAt(product.updatedAt())
      .enabled(product.enabled())
      .build();
  }

  public static Product mapToModel(ProductEntity product) {
    return Product.builder()
      .id(product.getId())
      .storeId(product.getStoreId())
      .name(product.getName())
      .price(product.getPrice())
      .stock(product.getStock())
      .description(product.getDescription())
      .photo(product.getPhoto())
      .createdAt(product.getCreatedAt())
      .updatedAt(product.getUpdatedAt())
      .enabled(product.getEnabled())
      .build();
  }
}
