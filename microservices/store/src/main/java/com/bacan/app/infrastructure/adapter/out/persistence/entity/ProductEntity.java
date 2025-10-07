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
@Table(name = "product", schema = "store")
public class ProductEntity {
  @Id
  private Long id;
  private String storeId;
  private String name;
  private Double price;
  private Integer stock;
  private String description;
  private String photo;
  @Column(value = "created_at")
  @CreatedDate
  private LocalDateTime createdAt;
  @Column(value = "updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;
  private Boolean enabled;
}
