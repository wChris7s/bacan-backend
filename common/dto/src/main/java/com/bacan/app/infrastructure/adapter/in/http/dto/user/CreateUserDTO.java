package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateUserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private Date birthDate;
  private String phone;
  private String email;
  private String password;
  private List<Long> roleIds;
}
