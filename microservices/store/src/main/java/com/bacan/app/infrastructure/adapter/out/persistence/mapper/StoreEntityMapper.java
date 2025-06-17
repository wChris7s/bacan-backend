package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class StoreEntityMapper {
  public static StoreEntity mapToEntity(Store store) {
    return StoreEntity.builder()
      .id(UUID.fromString(store.id()))
      .name(store.name())
      .open(store.open())
      .close(store.close())
      .story(store.story())
      .logo(store.logo())
      .background(store.background())
      .userId(store.userId())
      .createdAt(store.createdAt())
      .updatedAt(store.updatedAt())
      .enabled(store.enabled())
      .build();
  }

  public static Store mapToModel(StoreEntity store) {
    return Store.builder()
      .id(Objects.requireNonNull(store.getId()).toString())
      .name(store.getName())
      .open(store.getOpen())
      .close(store.getClose())
      .story(store.getStory())
      .logo(store.getLogo())
      .background(store.getBackground())
      .userId(store.getUserId())
      .createdAt(store.getCreatedAt())
      .updatedAt(store.getUpdatedAt())
      .enabled(store.isEnabled())
      .build();
  }
}


