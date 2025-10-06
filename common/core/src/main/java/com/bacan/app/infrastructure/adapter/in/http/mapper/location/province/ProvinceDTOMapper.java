package com.bacan.app.infrastructure.adapter.in.http.mapper.location.province;

import com.bacan.app.domain.models.location.province.Province;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.province.ProvinceDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProvinceDTOMapper {
  public static ProvinceDTO mapToDto(Province province) {
    return ProvinceDTO.builder()
      .id(province.id())
      .name(province.name())
      .stateId(province.stateId())
      .build();
  }
}
