package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductEntityMapper {
  ProductEntity toEntity(Product product);

  @Mapping(target = "store", ignore = true)
  @Mapping(target = "categories", ignore = true)
  Product toModel(ProductEntity product);
}
