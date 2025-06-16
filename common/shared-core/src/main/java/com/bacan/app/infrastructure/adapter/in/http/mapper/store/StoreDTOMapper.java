package com.bacan.app.infrastructure.adapter.in.http.mapper.store;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.CreateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreDTO;
import lombok.experimental.UtilityClass;


@UtilityClass
public class StoreDTOMapper {
  public static StoreDTO mapToDto(Store store) {
    return StoreDTO.builder()
      .id(store.getId())
      .userId(store.getUserId())
      .name(store.getName())
      .logo(store.getLogo())
      .open(store.getOpen())
      .close(store.getClose())
      .build();
  }

  public static Store mapToModel(CreateStoreDTO store) {
    return Store.builder()
      .name(store.getName())
      .build();
  }
}
