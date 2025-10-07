package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category", schema = "store")
public class ProductCategoryEntity {
  @Column(value = "product_id")
  private Long productId;
  @Column(value = "category_id")
  private Long categoryId;
}
