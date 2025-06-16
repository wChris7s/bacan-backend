package com.bacan.app.infrastructure.adapter.in.http.dto.location.country;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
  private Long id;
  private String name;
  private String phoneCode;
  private String langCode;
}
