package com.bacan.app.domain.model.user;

import com.bacan.app.domain.model.role.Role;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record User(
    String documentId,
    String name,
    String lastname,
    LocalDateTime birthDate,
    String phone,
    String email,
    String password,
    @With String profilePhoto,
    @With LocalDateTime createdAt,
    @With LocalDateTime updatedAt,
    @With boolean enabled,
    @With LocalDateTime passwordModifiedDate,
    @With List<Role> roles) {
}
