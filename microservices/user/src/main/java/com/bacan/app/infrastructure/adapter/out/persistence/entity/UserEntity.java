package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Builder
@Table(name = "user", schema = "usr")
public class UserEntity {
  @Id
  private Long id;

  @Column("document_id")
  private String documentId;

  private String name;

  private String lastname;

  private String phone;

  private String email;

  @Column("profile_photo_url")
  private String profilePhotoUrl;

  @Column("role_id")
  private Long roleId;

  @Column("address_id")
  private Long addressId;

  @Column("created_at")
  @CreatedDate
  private LocalDateTime createdAt;

  @Column("updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  private boolean enabled;
}
