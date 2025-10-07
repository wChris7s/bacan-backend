package com.bacan.app.infrastructure.adapter.in.http.mapper.category;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDTOMapper {

  // Category -> CategoryResponseDTO (para GET)
  public static CategoryResponseDTO mapToDto(Category category) {
    return CategoryResponseDTO.builder()
      .id(category.id())
      .name(category.name())
      .build();
  }

  // CategoryDTO -> Category (para POST / PUT)
  public static Category mapToDomain(CategoryDTO dto) {
    return Category.builder()
      .id(dto.getId())
      .name(dto.getName())
      .createdAt(dto.getCreatedAt())
      .updatedAt(dto.getUpdatedAt())
      .build();
  }
}
