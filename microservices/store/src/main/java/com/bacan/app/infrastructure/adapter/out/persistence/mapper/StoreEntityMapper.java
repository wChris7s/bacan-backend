package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StoreEntityMapper {
  StoreEntity toEntity(Store store);

  @Mapping(target = "user", ignore = true)
  Store toMapper(StoreEntity entity);
}
