package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.facades.ProductFacade;
import com.bacan.app.application.port.in.http.ProductUseCase;
import com.bacan.app.domain.queries.product.ProductQuery;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.product.ProductResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.product.ProductDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/bcn/api/product")
public class ProductController {
  private final ProductFacade productFacade;
  private final ProductUseCase productUseCase;

  public ProductController(ProductFacade productFacade, ProductUseCase productUseCase) {
    this.productFacade = productFacade;
    this.productUseCase = productUseCase;
  }

  @GetMapping()
  public Mono<Page<ProductResponseDTO>> getAllProducts(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size,
      @RequestParam(name = "direction", defaultValue = "ASC") String direction,
      @RequestParam(name = "property") String property,
      @RequestParam(name = "storeId", required = false) String storeId,
      @RequestParam(name = "categoryId", required = false) List<Long> categoryIds,
      @RequestParam(name = "name", required = false) String name) {

    if (Objects.isNull(categoryIds)) categoryIds = List.of();

    Sort sort = Sort.by(Sort.Direction.fromString(direction), property);
    Pageable pageable = PageRequest.of(page, size, sort);
    ProductQuery productQuery = ProductQuery.builder()
        .name(name)
        .storeId(storeId)
        .categoryIds(categoryIds)
        .pageable(pageable)
        .build();

    return productFacade.getAllProductsWithTheirCategoriesAndStore(productQuery)
        .map(ProductDTOMapper::mapToDto)
        .collectList()
        .zipWith(productUseCase.countAllProductsByQuery(productQuery))
        .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  @GetMapping("/{productId}")
  public Mono<ProductResponseDTO> getProductById(@PathVariable String productId) {
    return productFacade.getProductWithTheirCategoriesAndStoreById(productId)
        .map(ProductDTOMapper::mapToDto);
  }

  // === NEW: Create product ===
  @PostMapping
  public Mono<Void> createProduct(@RequestBody ProductDTO dto) {
    return productUseCase.createProduct(ProductDTOMapper.mapToDomain(dto))
        .then();
  }

  /* TODO: Create product */
}
