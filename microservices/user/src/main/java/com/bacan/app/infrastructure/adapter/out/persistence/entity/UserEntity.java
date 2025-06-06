package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "bacan")
public class UserEntity implements Persistable<String> {
  @Id
  @Column("document_id")
  private String documentId;
  private String name;
  private String lastname;
  private LocalDate birthdate;
  private String phone;
  private String email;
  private String password;
  @Column("profile_photo")
  private String profilePhoto;
  @Column("created_at")
  private LocalDateTime createdAt;
  @Column("updated_at")
  private LocalDateTime updatedAt;
  private boolean enabled;
  @Column("password_modified_date")
  private LocalDateTime passwordModifiedDate;
  @Column("phone_country_id")
  private Long phoneCountryId;

  @Transient
  private boolean isNew;

  @Override
  public String getId() {
    return documentId;
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }
}
