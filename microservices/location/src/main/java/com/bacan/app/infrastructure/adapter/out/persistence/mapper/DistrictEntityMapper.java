package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.location.district.District;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.DistrictEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DistrictEntityMapper {
  public static District mapToModel(DistrictEntity districtEntity) {
    return District.builder()
        .id(districtEntity.getId())
        .name(districtEntity.getName())
        .provinceId(districtEntity.getProvinceId())
        .stateId(districtEntity.getStateId())
        .build();
  }

  public static DistrictEntity mapToEntity(District district) {
    return DistrictEntity.builder()
        .id(district.id())
        .name(district.name())
        .provinceId(district.provinceId())
        .stateId(district.stateId())
        .build();
  }
}
