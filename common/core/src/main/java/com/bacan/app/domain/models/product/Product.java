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
  @With Long storeId,
  @With String name,
  @With Double price,
  @With Integer stock,
  @With String description,
  @With String photoUrl,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With Boolean enabled,
  @With List<Category> categories,
  @With Store store) {
}