package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.store;
import com.bacan.app.domain.model.store.Store;
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
                .opening(store.getOpening())
                .closing(store.getClosing())
                .build();
    }

    public static Store mapToModel(CreateStoreDTO store){
        return Store.builder()
                .name(store.getName())
                .build();
    }

}
