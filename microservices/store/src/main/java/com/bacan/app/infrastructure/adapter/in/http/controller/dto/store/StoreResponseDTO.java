package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import java.time.LocalDateTime;
import java.time.LocalTime;

/** DTO de salida para respuestas del Store */
public record StoreResponseDTO(
    String id,
    String name,
    LocalTime open,
    LocalTime close,
    String story,
    String logo,
    String background,
    String userId,
    boolean enabled,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
