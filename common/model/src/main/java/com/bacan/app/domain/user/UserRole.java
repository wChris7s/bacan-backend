package com.bacan.app.domain.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRole {
  Long userId;
  Long roleId;
}



