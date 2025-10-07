package com.bacan.app.infrastructure.adapter.in.http.dto.product;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class ProductDTO {
  private String id;
  private String storeId;
  private String name;
  private Double price;
  private Integer stock;
  private String description;
  private String photo;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
