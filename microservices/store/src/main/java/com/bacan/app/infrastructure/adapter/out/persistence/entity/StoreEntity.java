package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store", schema = "bacan")
public class StoreEntity implements Persistable<String> {
  @Id
  private String id;
  @Column(value = "user_id")
  private String userId;
  private String name;
  private String story;
  private String logo;
  private String background;
  private LocalTime open;
  private LocalTime close;
  @Column(value = "created_at")
  private LocalDateTime createdAt;
  @Column(value = "updated_at")
  private LocalDateTime updatedAt;
  private boolean enabled;

  @Transient
  private boolean isNew;
}
