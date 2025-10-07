package com.bacan.app.domain.models.product;

import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.models.store.Store;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Product(
  Long id,
  Long storeId,
  String name,
  Double price,
  Integer stock,
  String description,
  @With String photo,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With Boolean enabled,
  @With List<Category> categories,
  @With Store store) {
}