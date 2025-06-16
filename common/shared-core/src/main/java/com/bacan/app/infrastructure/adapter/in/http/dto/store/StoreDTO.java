package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class StoreDTO {
  private String id;
  private String userId;
  private String name;
  private String story;
  private String logo;
  private LocalTime open;
  private LocalTime close;
  private LocalDateTime created;
  private LocalDateTime updated;
  boolean enabled;
}
