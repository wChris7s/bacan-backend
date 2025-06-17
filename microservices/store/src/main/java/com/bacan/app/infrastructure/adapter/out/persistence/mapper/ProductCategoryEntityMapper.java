package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.product.ProductCategory;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductCategoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductCategoryEntityMapper {
  public static ProductCategoryEntity mapToEntity(ProductCategory productCategory) {
    return ProductCategoryEntity.builder()
      .productId(productCategory.productId())
      .categoryId(productCategory.categoryId())
      .build();
  }
}
