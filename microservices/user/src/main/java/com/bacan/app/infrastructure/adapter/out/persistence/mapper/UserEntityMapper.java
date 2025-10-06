package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.domain.models.role.Role;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserEntityMapper {
  @Mapping(target = "addressId", source = "address.id")
  @Mapping(target = "roleId", source = "role.id")
  UserEntity mapToEntity(User user);

  @Mapping(target = "address", source = "addressId")
  @Mapping(target = "role", source = "roleId")
  User mapToModel(UserEntity user);

  default Role toRole(Long userId) {
    return Role.builder()
      .id(userId)
      .build();
  }

  default Address toAddress(Long addressId) {
    return Address.builder()
      .id(addressId)
      .build();
  }
}
