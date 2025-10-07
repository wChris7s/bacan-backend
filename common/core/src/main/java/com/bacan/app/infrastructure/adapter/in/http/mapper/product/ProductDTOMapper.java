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

  // NEW: DTO -> Domain (para POST / futuro PUT)
  public static Product mapToDomain(ProductDTO dto) {
    return Product.builder()
        // id en create normalmente lo genera la DB/capa persistencia
        // .id(dto.getId()) // si tu Product de dominio lo acepta, puedes activarlo

        // storeId: si el dominio lo maneja como Long, convierte aquí:
        // .storeId(dto.getStoreId() == null ? null : Long.valueOf(dto.getStoreId()))
        .storeId(dto.getStoreId())

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
