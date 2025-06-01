package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table(name = "user_role", schema = "bacan")
public class UserRoleEntity {
  @Column(value = "user_id")
  String userId;
  @Column(value = "role_id")
  Long roleId;
}
