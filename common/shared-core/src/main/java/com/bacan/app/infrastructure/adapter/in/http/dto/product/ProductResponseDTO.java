package com.bacan.app.infrastructure.adapter.in.http.dto.product;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
  private String id;
  private String name;
  private Double price;
  private Integer stock;
  private String description;
  private String photo;
  private LocalDateTime createdAt;

  /* ======== Details ======== */
  private ProductStoreDTO store;
  private List<ProductCategoryDTO> categories;
  /* ========================= */
}
