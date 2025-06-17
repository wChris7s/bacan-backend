package com.bacan.app.domain.models.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {
  private List<T> content;
  private Long total;
}
