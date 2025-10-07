package com.bacan.app.infrastructure.adapter.in.http.mapper.product;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductStoreDTO;
import lombok.experimental.UtilityClass;

@UtilityClass 
public class ProductDTOMapper {

  // Domain -> Response DTO
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

  // DTO -> Domain (para POST / PUT)
  public static Product mapToDomain(ProductDTO dto) {
    return Product.builder()
        // id: en create suele ignorarse (lo genera la persistencia)
        // .id(dto.getId() == null ? null : Long.valueOf(dto.getId()))

        // storeId viene como String en el DTO y es Long en el dominio:
        .storeId(dto.getStoreId() == null || dto.getStoreId().isBlank() ? null : Long.valueOf(dto.getStoreId()))

        .name(dto.getName())
        .price(dto.getPrice())
        .stock(dto.getStock())
        .description(dto.getDescription())
        .photo(dto.getPhoto())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())
        .build();
  }
}
