package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.location.country.Country;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.CountryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryEntityMapper {
  public static Country mapToModel(CountryEntity countryEntity) {
    return Country.builder()
        .id(countryEntity.getId())
        .name(countryEntity.getName())
        .phoneCode(countryEntity.getPhoneCode())
        .langCode(countryEntity.getLangCode())
        .build();
  }

  public static CountryEntity mapToEntity(Country country) {
    return CountryEntity.builder()
        .id(country.id())
        .name(country.name())
        .phoneCode(country.phoneCode())
        .langCode(country.langCode())
        .build();
  }
}
