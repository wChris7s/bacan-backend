package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
@Value
@Builder
@Table(name="user",schema = "bacan")
public class UserEntity {
    @Id
    @Column(value = "document_id")
    Long documentId;
    String name;
    String lastname;
    @Column(value = "birth_date")
    LocalDateTime birthDate;
    String phone;
    String email;
    String password;
    @Column(value = "profile_photo")
    String profilePhoto;
    @With
    @Column(value = "created_at")
    LocalDateTime createdAt;
    @With
    @Column(value = "updated_at")
    LocalDateTime updatedAt;
    @With
    boolean enabled;
    @With
    @Column(value ="password_modified_date")
    LocalDateTime passwordModifiedDate;
}
