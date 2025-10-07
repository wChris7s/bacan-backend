package com.bacan.app.domain.models.user;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.domain.models.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {
  private final String id;
  private final String documentId;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String profilePhotoUrl;
  private final Role role;
  private Address address;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean enabled;
}
