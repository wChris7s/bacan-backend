package com.bacan.app.domain.queries.product;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
public class ProductQuery {
  private final Pageable pageable;
  private final String name;
  private final String storeId;
  private final List<Long> categoryIds;
}
