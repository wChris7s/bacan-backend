package com.bacan.app.infrastructure.adapter.in.http.mapper.location.country;

import com.bacan.app.domain.models.location.country.Country;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.country.CountryDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryDTOMapper {
  public static CountryDTO mapToDto(Country country) {
    return CountryDTO.builder()
      .id(country.id())
      .name(country.name())
      .phoneCode(country.phoneCode())
      .langCode(country.langCode())
      .build();
  }
}
