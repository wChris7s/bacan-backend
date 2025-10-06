package com.bacan.app.infrastructure.adapter.in.http.dto.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRoleDTO {
  private String name;
}
