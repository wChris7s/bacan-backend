package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import com.bacan.app.infrastructure.adapter.in.http.dto.address.AddressDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.RoleDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private String documentId;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String profilePhotoUrl;
  private RoleDTO role;
  private AddressDTO address;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean enabled;
}
