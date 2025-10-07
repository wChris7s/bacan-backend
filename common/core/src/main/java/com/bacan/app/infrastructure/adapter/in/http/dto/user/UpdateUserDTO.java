package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import com.bacan.app.infrastructure.adapter.in.http.dto.address.UpdateAddressDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private UpdateAddressDTO address;
}
