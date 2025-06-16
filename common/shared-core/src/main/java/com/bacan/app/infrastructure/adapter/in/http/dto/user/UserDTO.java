package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDTO {
  String documentId;
  String name;
  String lastname;
  LocalDate birthDate;
  String phone;
  String email;
  String profilePhoto;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
}
