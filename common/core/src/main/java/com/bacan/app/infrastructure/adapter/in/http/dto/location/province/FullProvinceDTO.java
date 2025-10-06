package com.bacan.app.infrastructure.adapter.in.http.dto.location.province;

import com.bacan.app.infrastructure.adapter.in.http.dto.location.state.FullStateDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullProvinceDTO {
  private String id;
  private String name;
  private FullStateDTO state;
}
