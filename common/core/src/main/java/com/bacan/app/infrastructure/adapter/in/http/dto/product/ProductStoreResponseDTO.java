package com.bacan.app.infrastructure.adapter.in.http.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreResponseDTO {
  private String name;
  private String logoUrl;
}
