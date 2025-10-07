package com.bacan.app.infrastructure.adapter.in.http.controller.dto.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

/**
 * DTO de entrada para creación o actualización de tiendas.
 * Se usa en los controladores HTTP.
 */
@Data
@Builder
public class StoreRequest {

  @NotBlank(message = "El nombre de la tienda es obligatorio")
  @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
  private String name;

  @NotNull(message = "La hora de apertura es obligatoria")
  private LocalTime open;

  @NotNull(message = "La hora de cierre es obligatoria")
  private LocalTime close;

  @Size(max = 500, message = "La historia no puede superar los 500 caracteres")
  private String story;

  private String logo;
  private String background;

  @NotBlank(message = "El número de documento del usuario es obligatorio")
  private String documentId;

  private boolean enabled;
}
