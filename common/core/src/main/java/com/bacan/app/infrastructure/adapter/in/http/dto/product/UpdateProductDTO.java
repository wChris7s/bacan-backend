package com.bacan.app.infrastructure.adapter.in.http.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
  private String name;
  private Double price;
  private Integer stock;
  private String description;
}
