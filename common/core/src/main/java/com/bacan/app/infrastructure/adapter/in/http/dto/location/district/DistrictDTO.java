package com.bacan.app.infrastructure.adapter.in.http.dto.location.district;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
  private String id;
  private String name;
  private String provinceId;
}
