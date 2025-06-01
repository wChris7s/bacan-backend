package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDTO {
  String documentId;
  String name;
  String lastname;
  LocalDateTime birthDate;
  String phone;
  String email;
  String profilePhoto;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
}
