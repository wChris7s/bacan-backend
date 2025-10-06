package com.bacan.app.domain.models.address;

import com.bacan.app.domain.models.location.district.District;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Address {
  private Long id;
  private District district;
  private String street;
  private String postalCode;
  private String number;
  private String reference;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean enabled;
}
