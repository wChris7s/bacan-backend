package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.location.country;

import com.bacan.app.domain.model.location.country.Country;
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
