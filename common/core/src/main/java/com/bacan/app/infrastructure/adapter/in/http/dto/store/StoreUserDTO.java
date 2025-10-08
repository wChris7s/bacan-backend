package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserDTO {
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String profilePhotoUrl;
}
