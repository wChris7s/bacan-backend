package com.bacan.app.domain.models.product;

import lombok.Builder;

@Builder
public record ProductCategory(
  Long productId,
  Long categoryId) {
}
