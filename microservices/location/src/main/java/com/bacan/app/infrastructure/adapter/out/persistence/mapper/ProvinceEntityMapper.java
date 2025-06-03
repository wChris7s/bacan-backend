package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.location.province.Province;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.ProvinceEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProvinceEntityMapper {
  public static Province mapToModel(ProvinceEntity provinceEntity) {
    return Province.builder()
        .id(provinceEntity.getId())
        .name(provinceEntity.getName())
        .stateId(provinceEntity.getStateId())
        .build();
  }

  public static ProvinceEntity mapToEntity(Province province) {
    return ProvinceEntity.builder()
        .id(province.id())
        .name(province.name())
        .stateId(province.stateId())
        .stateId(province.stateId())
        .build();
  }
}
