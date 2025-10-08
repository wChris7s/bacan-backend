package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.domain.models.category.Category;
import com.bacan.app.domain.queries.category.CategoryQuery;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CategoryResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.CreateCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.category.UpdateCategoryDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.category.CategoryDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryUseCase categoryUseCase;

  @GetMapping
  @Operation(summary = "Get all categories")
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
      .map(CategoryDTOMapper::map)
      .collectList()
      .zipWith(categoryUseCase.countAllCategoriesByQuery(categoryQuery))
      .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  @GetMapping("/{categoryId}")
  @Operation(summary = "Get category by id")
  public Mono<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
    return categoryUseCase.getCategoryById(categoryId)
      .map(CategoryDTOMapper::map);
  }

  @PostMapping
  @Operation(summary = "Create category")
  public Mono<Void> createCategory(@RequestBody CreateCategoryDTO dto) {
    Category category = CategoryDTOMapper.map(dto);
    return categoryUseCase.createCategory(category).then();
  }

  @PutMapping("/{categoryId}")
  @Operation(summary = "Update category by id")
  public Mono<CategoryResponseDTO> updateCategory(
    @PathVariable Long categoryId,
    @RequestBody UpdateCategoryDTO dto) {
    Category toUpdate = CategoryDTOMapper.map(dto);
    return categoryUseCase.updateCategory(categoryId, toUpdate)
      .map(CategoryDTOMapper::map);
  }

  @GetMapping("/product/{productId}")
  @Operation(summary = "Get categories by product id")
  public Mono<java.util.List<CategoryResponseDTO>> getCategoriesByProductId(@PathVariable Long productId) {
    return categoryUseCase.getCategoriesByProductId(productId)
      .map(CategoryDTOMapper::map)
      .collectList();
  }
}
 