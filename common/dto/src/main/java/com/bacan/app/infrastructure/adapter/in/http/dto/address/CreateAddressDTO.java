package com.bacan.app.infrastructure.adapter.in.http.dto.address;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDTO {
  private Long countryId;
  private String stateId;
  private String provinceId;
  private String districtId;
  private String postalCode;
  private String street;
  private String number;
  private String reference;
}
