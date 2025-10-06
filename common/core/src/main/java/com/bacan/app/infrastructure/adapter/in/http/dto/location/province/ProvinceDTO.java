package com.bacan.app.infrastructure.adapter.in.http.dto.location.province;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {
  private String id;
  private String name;
  private String stateId;
}
