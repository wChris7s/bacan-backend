package com.bacan.app.domain.models.user;

import lombok.Builder;
import lombok.With;

@Builder
public record UserRole(
  @With String userId,
  @With Long roleId) {
}
