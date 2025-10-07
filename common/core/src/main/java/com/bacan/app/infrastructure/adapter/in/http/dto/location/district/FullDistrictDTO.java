package com.bacan.app.infrastructure.adapter.in.http.dto.location.district;

import com.bacan.app.infrastructure.adapter.in.http.dto.location.province.FullProvinceDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullDistrictDTO {
  private String id;
  private String name;
  private FullProvinceDTO province;
}
