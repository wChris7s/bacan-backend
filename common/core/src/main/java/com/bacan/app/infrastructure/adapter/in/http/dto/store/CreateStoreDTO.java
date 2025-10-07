package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreDTO {
  private Long userId;
  private String name;
  private String story;
  private String open;
  private String close;
}
