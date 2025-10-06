package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressEntityMapper {
  @Mapping(target = "districtId", source = "district.id")
  AddressEntity mapToEntity(Address address);

  @Mapping(target = "district", source = "districtId")
  Address mapToModel(AddressEntity address);

  default District toDistrict(String districtId) {
    return districtId == null ? null :
      District.builder().id(districtId).build();
  }
}
