package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "bacan")
public class ProductEntity implements Persistable<UUID> {
  @Id
  private UUID id;
  private String storeId;
  private String name;
  private Double price;
  private Integer stock;
  private String description;
  private String photo;
  @Column(value = "created_at")
  private LocalDateTime createdAt;
  @Column(value = "updated_at")
  private LocalDateTime updatedAt;

  @Transient
  private boolean isNew;
}
