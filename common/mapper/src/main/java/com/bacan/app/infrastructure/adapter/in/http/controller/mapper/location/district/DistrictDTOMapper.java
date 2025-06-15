package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.location.district;

import com.bacan.app.domain.model.location.district.District;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.DistrictDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DistrictDTOMapper {
  public static DistrictDTO mapToDto(District district) {
    return DistrictDTO.builder()
        .id(district.id())
        .name(district.name())
        .provinceId(district.provinceId())
        .stateId(district.stateId())
        .build();
  }
}
