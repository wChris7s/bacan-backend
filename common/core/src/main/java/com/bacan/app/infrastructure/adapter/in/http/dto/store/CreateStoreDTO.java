package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreDTO {
  private String name;
  private LocalTime open;
  private LocalTime close;
  private String story;
}
