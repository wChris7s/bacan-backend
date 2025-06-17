package com.bacan.app.infrastructure.adapter.in.http.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStoreDTO {
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private String photo;
}
