package com.bacan.app.infrastructure.adapter.in.http.mapper.address;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.infrastructure.adapter.in.http.dto.address.CreateAddressDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.address.UpdateAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressDTOMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "district", source = "districtId")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  Address mapToModel(CreateAddressDTO address);

  @Mapping(target = "district", source = "districtId")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  Address mapToModel(UpdateAddressDTO address);

  default District toDistrict(String districtId) {
    return District.builder()
      .id(districtId)
      .build();
  }
}
