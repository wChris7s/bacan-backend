package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreEntityMapper {

  public static StoreEntity mapToEntity(Store store) {
    return StoreEntity.builder()
      .id(store.id())
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

  public static Store mapToModel(StoreEntity entity) {
    return Store.builder()
      .id(entity.getId())
      .name(entity.getName())
      .open(entity.getOpen())
      .close(entity.getClose())
      .story(entity.getStory())
      .logo(entity.getLogo())
      .background(entity.getBackground())
      .userId(entity.getUserId())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .enabled(entity.getEnabled())
      .build();
  }
}
