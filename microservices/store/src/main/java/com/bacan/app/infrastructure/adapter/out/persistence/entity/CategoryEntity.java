package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category", schema = "bacan")
public class CategoryEntity {
  @Id
  private Long id;
  private String name;
  @Column(value = "created_at")
  private LocalDateTime createdAt;
  @Column(value = "updated_at")
  private LocalDateTime updatedAt;
}
