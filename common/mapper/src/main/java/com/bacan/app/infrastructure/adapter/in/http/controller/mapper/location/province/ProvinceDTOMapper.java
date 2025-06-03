package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.location.province;

import com.bacan.app.domain.model.location.province.Province;
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
