package com.bacan.app.infrastructure.adapter.in.http.dto.category;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
  private Long id;
  private String name;
}
