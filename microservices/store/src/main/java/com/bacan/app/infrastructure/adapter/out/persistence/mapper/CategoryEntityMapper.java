package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryEntityMapper {
  CategoryEntity toEntity(Category category);

  Category toModel(CategoryEntity category);
}
