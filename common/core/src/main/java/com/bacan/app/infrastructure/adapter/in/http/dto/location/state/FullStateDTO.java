package com.bacan.app.infrastructure.adapter.in.http.dto.location.state;

import com.bacan.app.infrastructure.adapter.in.http.dto.location.country.CountryDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullStateDTO {
  private String id;
  private String name;
  private CountryDTO country;
}
