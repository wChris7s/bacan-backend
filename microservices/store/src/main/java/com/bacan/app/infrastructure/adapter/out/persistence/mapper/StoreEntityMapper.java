package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreEntityMapper {
  public static StoreEntity mapToEntity(Store store) {
    return StoreEntity.builder()
      .id(store.getId())
      .userId(store.getUserId())
      .name(store.getName())
      .story(store.getStory())
      .logo(store.getLogo())
      .open(store.getOpen())
      .close(store.getClose())
      .createdAt(store.getCreatedAt())
      .updatedAt(store.getUpdatedAt())
      .enabled(store.isEnabled())
      .build();
  }

  public static Store mapToModel(StoreEntity store) {
    return Store.builder()
      .id(store.getId())
      .userId(store.getUserId())
      .name(store.getName())
      .story(store.getStory())
      .logo(store.getLogo())
      .open(store.getOpen())
      .close(store.getClose())
      .createdAt(store.getCreatedAt())
      .updatedAt(store.getUpdatedAt())
      .enabled(store.isEnabled())
      .build();
  }
}


