package com.bacan.app.infrastructure.adapter.in.http.mapper.category;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDTOMapper {
  public static CategoryResponseDTO mapToDto(Category category) {
    return CategoryResponseDTO.builder()
      .id(category.id())
      .name(category.name())
      .build();
  }
}
