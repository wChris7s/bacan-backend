package com.bacan.app.infrastructure.adapter.in.http.dto.address;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
  private String id;
  private String userId;
  private Long countryId;
  private String stateId;
  private String provinceId;
  private String districtId;
  private String street;
  private String postalCode;
  private String number;
  private String reference;
  @With private LocalDateTime createdAt;
  @With private LocalDateTime updatedAt;
}
