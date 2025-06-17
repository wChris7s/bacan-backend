package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category", schema = "bacan")
public class ProductCategoryEntity {
  @Column(value = "product_id")
  private UUID productId;
  @Column(value = "category_id")
  private Long categoryId;
}
