package com.bacan.app.domain.models.product;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductCategory(
  UUID productId,
  Long categoryId) {
}
