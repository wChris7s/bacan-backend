package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import com.bacan.app.infrastructure.adapter.in.http.dto.address.CreateAddressDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private LocalDateTime birthdate;
  private Long phoneCountryId;
  private String phone;
  private String email;
  private String password;
  private Long roleId;
  private CreateAddressDTO address;
}
