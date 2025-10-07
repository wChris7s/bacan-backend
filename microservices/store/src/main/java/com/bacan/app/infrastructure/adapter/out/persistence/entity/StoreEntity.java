package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Entidad de persistencia que representa la tabla "bacan.store".
 * Compatible con PostgreSQL + R2DBC.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "bacan", name = "store")
public class StoreEntity {

  /** Identificador único (UUID). Generado en DB por defecto con uuid_generate_v4(). */
  @Id
  @Column("id")
  private UUID id;

  @Column("name")
  private String name;

  /** Hora de apertura. */
  @Column("open")
  private LocalTime open;

  /** Hora de cierre. */
  @Column("close")
  private LocalTime close;

  /** Descripción o historia de la tienda. */
  @Column("story")
  private String story;

  @Column("logo")
  private String logo;

  @Column("background")
  private String background;

  /** ID del usuario dueño (FK hacia bacan.user.document_id). */
  @Column("user_id")
  private String userId;

  @Column("created_at")
  private LocalDateTime createdAt;

  @Column("updated_at")
  private LocalDateTime updatedAt;

  /** Estado lógico de habilitación. */
  @Column("enabled")
  private Boolean enabled;
}
