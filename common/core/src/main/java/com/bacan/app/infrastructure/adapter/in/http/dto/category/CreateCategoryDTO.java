package com.bacan.app.infrastructure.adapter.in.http.dto.category;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDTO {
  private String name;
}
