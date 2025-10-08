package com.bacan.app.infrastructure.adapter.in.http.mapper.product;

import com.bacan.app.domain.models.product.Product;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductStoreDTO;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductDTOMapper {

  // Domain -> Response DTO
  public static ProductResponseDTO mapToDto(Product product) {
    return ProductResponseDTO.builder()
        // id del dominio (Long) -> String en el ResponseDTO
        .id(product.id() == null ? null : String.valueOf(product.id()))
        .name(product.name())
        .price(product.price())
        .stock(product.stock())
        .description(product.description())
        .photo(product.photo())
        .createdAt(product.createdAt())
        .store(
            product.store() == null
                ? null
                : ProductStoreDTO.builder()
                    .name(product.store().name())
                    .logo(product.store().logo())
                    .build()
        )
        .categories(
            product.categories() == null
                ? List.of()
                : product.categories().stream()
                    .map(category -> ProductCategoryDTO.builder()
                        .name(category.name())
                        .build())
                    .toList()
        )
        .build();
  }

  // DTO -> Domain (para POST / PUT)
  public static Product mapToDomain(ProductDTO dto) {
    Long id = dto.getId() == null || dto.getId().isBlank() ? null : parseLong(dto.getId());
    Long storeId = dto.getStoreId() == null || dto.getStoreId().isBlank() ? null : parseLong(dto.getStoreId());

    return Product.builder()
        .id(id)                 // si no quieres permitir setear id en create, puedes ignorarlo
        .storeId(storeId)
        .name(dto.getName())
        .price(dto.getPrice())
        .stock(dto.getStock())
        .description(dto.getDescription())
        .photo(dto.getPhoto())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())
        .build();
  }

  private static Long parseLong(String v) {
    try { return Long.valueOf(v); } catch (Exception e) { return null; }
  }
}
