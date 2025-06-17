package com.bacan.app.domain.queries.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
public class StoreQuery {
  private final String name;
  private final Pageable pageable;
}
