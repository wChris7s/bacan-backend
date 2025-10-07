package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.store;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreRequest;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreResponseDTO;

/**
 * Mapper entre DTOs HTTP y el modelo de dominio Store.
 */
public final class StoreDTOMapper {
  private StoreDTOMapper() {
  }

  // ------ HTTP -> Dominio (para crear)
  public static Store toDomain(StoreRequest dto) {
    return Store.builder()
      .id(null)
      .name(dto.getName())
      .open(dto.getOpen())
      .close(dto.getClose())
      .story(dto.getStory())
      .logo(dto.getLogo())
      .background(dto.getBackground())
      .userId(dto.getDocumentId())
      .createdAt(null)   // se setea en el adapter
      .updatedAt(null)   // se setea en el adapter
      .enabled(dto.isEnabled())
      .build();
  }

  // ------ HTTP -> Dominio (para actualizar)
  public static Store toDomain(String storeId, StoreRequest dto) {
    return Store.builder()
      .id(storeId)
      .name(dto.getName())
      .open(dto.getOpen())
      .close(dto.getClose())
      .story(dto.getStory())
      .logo(dto.getLogo())
      .background(dto.getBackground())
      .userId(dto.getDocumentId())
      .createdAt(null)
      .updatedAt(null)
      .enabled(dto.isEnabled())
      .build();
  }

  // ------ Dominio -> HTTP (para respuestas)
  public static StoreResponseDTO mapToDto(Store store) {
    return new StoreResponseDTO(
      store.id(),
      store.name(),
      store.open(),
      store.close(),
      store.story(),
      store.logo(),
      store.background(),
      store.userId(),
      store.enabled(),
      store.createdAt(),
      store.updatedAt()
    );
  }
}
