package com.bacan.app.infrastructure.adapter.in.http.mapper.store;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.in.http.controller.dto.store.StoreRequest;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreUserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass 
public class StoreDTOMapper {

  // Domain -> Response DTO (para GETs)
  public static StoreResponseDTO mapToDto(Store store) {
    return StoreResponseDTO.builder()
      .id(store.id())
      .name(store.name())
      .open(store.open())
      .close(store.close())
      .story(store.story())
      .logo(store.logo())
      .background(store.background())
      .user(StoreUserDTO.builder()
        .name(store.user().getName())
        .lastname(store.user().getLastname())
        .phone(store.user().getPhone())
        .email(store.user().getEmail())
        .photo(store.user().getProfilePhotoUrl())
        .build())
      .build();
  }

  // Request -> Domain (para POST / futuro PUT)
  public static Store toDomain(StoreRequest req) {
    return Store.builder()
      .name(req.getName())
      .open(req.getOpen())
      .close(req.getClose())
      .story(req.getStory())
      .logo(req.getLogo())
      .background(req.getBackground())
      .enabled(req.isEnabled())
      // OJO: req.getDocumentId() se usa en capa de aplicación/fachada
      // para resolver el usuario (tu compañero dejó el adaptador de user).
      // Aquí NO resolvemos el usuario.
      .build();
  }
}
