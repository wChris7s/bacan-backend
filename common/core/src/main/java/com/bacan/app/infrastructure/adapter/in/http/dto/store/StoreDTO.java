package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
  private String id;
  private String name;
  private LocalTime open;
  private LocalTime close;
  private String story;
  private String logo;
  private String background;
  private LocalDateTime created;
  private LocalDateTime updated;
  private boolean enabled;
}
