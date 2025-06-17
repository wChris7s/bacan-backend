package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import com.bacan.app.infrastructure.adapter.in.http.dto.address.AddressDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.RoleDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedUserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private LocalDate birthDate;
  private String phone;
  private String email;
  private String photo;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  boolean enabled;
  LocalDateTime passwordModifiedDate;
  RoleDTO role;
  AddressDTO address;
  Long phoneCountryId;
}
