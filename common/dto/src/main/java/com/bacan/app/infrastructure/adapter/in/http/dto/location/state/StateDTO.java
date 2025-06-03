package com.bacan.app.infrastructure.adapter.in.http.dto.location.state;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
  private String id;
  private String name;
  private Long countryId;
}
