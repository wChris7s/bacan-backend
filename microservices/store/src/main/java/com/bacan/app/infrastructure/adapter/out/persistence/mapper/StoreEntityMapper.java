package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class StoreEntityMapper {

  public static StoreEntity mapToEntity(Store store) {
    return StoreEntity.builder()
        .id(Optional.ofNullable(store.id())
            .filter(id -> !id.isEmpty())
            .map(UUID::fromString)
            .orElse(null))
        .name(store.name())
        .open(store.open())
        .close(store.close())
        .story(store.story())
        .logo(store.logo())
        .background(store.background())
        .userId(store.userId())
        .createdAt(store.createdAt())
        .updatedAt(store.updatedAt())
        // store.enabled() es boolean primitivo: úsalo directo
        .enabled(store.enabled())
        .build();
  }

  public static Store mapToModel(StoreEntity entity) {
    return Store.builder()
        .id(Objects.toString(entity.getId(), null))
        .name(entity.getName())
        .open(entity.getOpen())
        .close(entity.getClose())
        .story(entity.getStory())
        .logo(entity.getLogo())
        .background(entity.getBackground())
        .userId(entity.getUserId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        // La entidad tiene Boolean; el dominio espera boolean
        .enabled(Boolean.TRUE.equals(entity.getEnabled()))
        .build();
  }
}
