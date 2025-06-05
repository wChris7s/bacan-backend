package com.bacan.app.domain.model.store;
import lombok.Builder;
import lombok.With;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Value
@Builder
public class Store {
    Long id;
    String userId;
    String name;
    String story;
    String logo;
    String opening;
    String closing;
    @With
    LocalDateTime createdAt;
    @With
    LocalDateTime updatedAt;
    @With
    boolean enabled;
}

