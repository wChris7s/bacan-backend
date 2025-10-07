package com.bacan.app.infrastructure.adapter.in.http.dto.address;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDTO {
  private String districtId;
  private String street;
  private String postalCode;
  private String number;
  private String reference;
}
