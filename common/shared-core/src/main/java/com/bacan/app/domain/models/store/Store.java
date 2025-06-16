package com.bacan.app.domain.models.store;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@Builder
public class Store {
  String id;
  String userId;
  String name;
  String story;
  String logo;
  LocalTime open;
  LocalTime close;
  @With
  LocalDateTime createdAt;
  @With
  LocalDateTime updatedAt;
  @With
  boolean enabled;
}

