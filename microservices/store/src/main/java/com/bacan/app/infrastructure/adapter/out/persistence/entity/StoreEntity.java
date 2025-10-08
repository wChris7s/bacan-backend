package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store", schema = "store")
public class StoreEntity {
  @Id
  private Long id;
  private String name;
  private LocalTime open;
  private LocalTime close;
  private String story;
  @Column("logo_url")
  private String logoUrl;
  @Column("background_url")
  private String backgroundUrl;
  @Column("document_id")
  private String documentId;
  @Column("created_at")
  @CreatedDate
  private LocalDateTime createdAt;
  @Column("updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;
  private Boolean enabled;
}
