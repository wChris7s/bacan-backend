package com.bacan.app.infrastructure.adapter.in.http.mapper.location.district;

import com.bacan.app.domain.models.location.district.District;
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
