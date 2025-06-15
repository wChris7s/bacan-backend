package com.bacan.app.infrastructure.adapter.in.http.dto.role;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
  private Long id;
  private String name;
}
