package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category", schema = "store")
public class CategoryEntity {
  @Id
  private Long id;
  private String name;
  @Column("created_at")
  @CreatedDate
  private LocalDateTime createdAt;
  @Column("updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;
  private Boolean enabled;
}
