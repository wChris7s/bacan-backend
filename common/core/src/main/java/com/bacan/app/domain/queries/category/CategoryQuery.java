package com.bacan.app.domain.queries.category;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
public class CategoryQuery {
  private final Pageable pageable;
  private final String name;
}
