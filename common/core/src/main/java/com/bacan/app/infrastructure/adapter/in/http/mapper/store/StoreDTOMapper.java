package com.bacan.app.infrastructure.adapter.in.http.mapper.store;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.CreateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.UpdateStoreDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreDTOMapper {
  public static StoreResponseDTO mapToDto(Store store) {
    return StoreResponseDTO.builder()
      .id(store.id())
      .name(store.name())
      .open(store.open())
      .close(store.close())
      .story(store.story())
      .logoUrl(store.logoUrl())
      .backgroundUrl(store.backgroundUrl())
      .user(
        store.user() == null ? null :
          StoreUserDTO.builder()
            .name(store.user().getName())
            .lastname(store.user().getLastname())
            .phone(store.user().getPhone())
            .email(store.user().getEmail())
            .profilePhotoUrl(store.user().getProfilePhotoUrl())
            .build()
      )
      .build();
  }

  public static Store map(CreateStoreDTO dto) {
    return Store.builder()
      .name(dto.getName())
      .open(dto.getOpen())
      .close(dto.getClose())
      .story(dto.getStory())
      .build();
  }

  public static Store map(UpdateStoreDTO dto) {
    return Store.builder()
      .open(dto.getOpen())
      .close(dto.getClose())
      .story(dto.getStory())
      .build();
  }
}
