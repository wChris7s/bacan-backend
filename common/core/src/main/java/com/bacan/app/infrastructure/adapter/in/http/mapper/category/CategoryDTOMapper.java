package com.bacan.app.infrastructure.adapter.in.http.mapper.category;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CreateCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.UpdateCategoryDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDTOMapper {
  public static CategoryResponseDTO map(Category category) {
    return CategoryResponseDTO.builder()
      .id(category.id())
      .name(category.name())
      .build();
  }

  public static Category map(CreateCategoryDTO dto) {
    return Category.builder()
      .name(dto.getName())
      .build();
  }

  public static Category map(UpdateCategoryDTO dto) {
    return Category.builder()
      .name(dto.getName())
      .build();
  }
}
