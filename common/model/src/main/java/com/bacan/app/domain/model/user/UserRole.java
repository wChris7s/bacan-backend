package com.bacan.app.domain.model.user;

import lombok.Builder;
import lombok.With;

@Builder
public record UserRole(
    @With Long userId,
    @With Long roleId) {
}
