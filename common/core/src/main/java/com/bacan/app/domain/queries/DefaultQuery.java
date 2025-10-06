package com.bacan.app.domain.queries;

import com.bacan.app.domain.models.page.Sort;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DefaultQuery {
  private final Sort sort;
  private final Integer page;
  private final Integer size;
}
