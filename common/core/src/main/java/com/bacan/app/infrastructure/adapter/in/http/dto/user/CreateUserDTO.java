package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import com.bacan.app.infrastructure.adapter.in.http.dto.address.CreateAddressDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private Long roleId;
  private CreateAddressDTO address;
}
