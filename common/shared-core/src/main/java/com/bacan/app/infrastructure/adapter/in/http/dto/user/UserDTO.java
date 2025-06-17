package com.bacan.app.infrastructure.adapter.in.http.dto.user;

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
  private String photo;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean enabled;
  private LocalDateTime passwordModifiedDate;
  private Long phoneCountryId;
}
