package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateUserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private LocalDateTime birthDate;
  private String phone;
  private String email;
  private String password;
  private List<Long> roleIds;

  public CreateUserDTO() {
  }

  public CreateUserDTO(String documentId, String name, String lastname, LocalDateTime birthDate, String phone, String email, String password, List<Long> roleIds) {
    this.documentId = documentId;
    this.name = name;
    this.lastname = lastname;
    this.birthDate = birthDate;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.roleIds = roleIds;
  }
}
