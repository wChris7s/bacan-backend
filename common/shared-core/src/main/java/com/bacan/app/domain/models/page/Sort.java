package com.bacan.app.domain.models.page;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Sort {
  private String property;
  private Direction direction;
}
