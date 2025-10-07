package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.domain.queries.category.CategoryQuery;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.category.CategoryDTOMapper;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/category")
public class CategoryController {
  private final CategoryUseCase categoryUseCase;

  public CategoryController(CategoryUseCase categoryUseCase) {
    this.categoryUseCase = categoryUseCase;
  }

  @GetMapping
  public Mono<Page<CategoryResponseDTO>> getCategories(
    @RequestParam(name = "page", defaultValue = "0") Integer page,
    @RequestParam(name = "size", defaultValue = "5") Integer size,
    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
    @RequestParam(name = "property") String property,
    @RequestParam(name = "name", required = false, defaultValue = "") String name) {

    Sort sort = Sort.by(Sort.Direction.fromString(direction), property);
    Pageable pageable = PageRequest.of(page, size, sort);
    CategoryQuery categoryQuery = CategoryQuery.builder()
      .name(name)
      .pageable(pageable)
      .build();

    return categoryUseCase.getAllCategoriesByQuery(categoryQuery)
      .map(CategoryDTOMapper::mapToDto)
      .collectList()
      .zipWith(categoryUseCase.countAllCategoriesByQuery(categoryQuery))
      .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  /* TODO: Get category by Id */

  /* TODO: Create category */

  /* TODO: Assign category to a product using category id and product id */
}
