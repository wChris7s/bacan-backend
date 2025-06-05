package com.bacan.app.domain.model.role;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Builder
public class Role {
    Long id;
    String name;
    @With
    LocalDateTime createdAt;
    @With
    LocalDateTime updatedAt;
    @With
    boolean enabled;
    boolean isPublic;
}
