package com.bacan.app.domain.user;

import com.bacan.app.domain.role.Role;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class User {
  Long documentId;
  String name;
  String lastname;
  LocalDateTime birthDate;
  String phone;
  String email;
  String password;
  String profilePhoto;
  @With
  LocalDateTime createdAt;
  @With
  LocalDateTime updatedAt;
  @With
  boolean enabled;
  @With
  LocalDateTime passwordModifiedDate;
  @With
  List<Role> roles;
}
