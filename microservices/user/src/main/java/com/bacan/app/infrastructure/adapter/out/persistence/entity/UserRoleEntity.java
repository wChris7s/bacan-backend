package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table(name="userRole",schema = "bacan")
public class UserRoleEntity {
    @Id
    @Column(value = "user_id")
    Long userId;
    @Column(value = "rol_id")
    Long rolId;

}
