package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;

public class StoreEntityMapper {

    public static StoreEntity mapToEntity(Store store) {
        return StoreEntity.builder()
                .id(store.getId())
                .userId(store.getUserId())
                .name(store.getName())
                .story(store.getStory())
                .logo(store.getLogo())
                .opening(store.getOpening())
                .closing(store.getClosing())
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
                .opening(store.getOpening())
                .closing(store.getClosing())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .enabled(store.isEnabled())
                .build();
    }

}


