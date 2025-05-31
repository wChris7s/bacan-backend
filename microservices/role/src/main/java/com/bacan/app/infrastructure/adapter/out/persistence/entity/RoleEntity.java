package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Value
@Builder
@Table(name = "role", schema = "bacan")
public class RoleEntity {
    @Id
    Long id;
    String name;
    @Column(value = "created_at")
    LocalDateTime createdAt;
    @Column(value = "updated_at")
    LocalDateTime updatedAt;
    boolean enabled;
}
