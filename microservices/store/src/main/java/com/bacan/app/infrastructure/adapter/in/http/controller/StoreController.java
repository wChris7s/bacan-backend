package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.facades.StoreFacade;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.CreateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.UpdateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.store.StoreDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "/bcn/api/store")
@RequiredArgsConstructor
public class StoreController {

  private final StoreFacade storeFacade;

  @GetMapping
  public Mono<Page<StoreResponseDTO>> getAllStores(
    @RequestParam(name = "page", defaultValue = "0") Integer page,
    @RequestParam(name = "size", defaultValue = "5") Integer size,
    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
    @RequestParam(name = "property") String property,
    @RequestParam(name = "name", required = false) String name) {

    Sort sort = Sort.by(Sort.Direction.fromString(direction), property);
    Pageable pageable = PageRequest.of(page, size, sort);

    StoreQuery storeQuery = StoreQuery.builder()
      .name(name)
      .pageable(pageable)
      .build();

    return storeFacade.getAllStoresWithUserByQuery(storeQuery)
      .map(StoreDTOMapper::mapToDto)
      .collectList()
      .zipWith(storeFacade.countAllStoresByQuery(storeQuery))
      .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  @GetMapping("/{storeId}")
  public Mono<StoreResponseDTO> getStoreById(@PathVariable Long storeId) {
    return storeFacade.getStoreWithUserById(storeId)
      .map(StoreDTOMapper::mapToDto);
  }

  @PostMapping("/user/{documentId}")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> create(
    @PathVariable String documentId,
    @RequestBody CreateStoreDTO dto
  ) {
    Store store = StoreDTOMapper.map(dto);
    return storeFacade.createStore(documentId, store)
      .then();
  }

  @PutMapping("/{storeId}/user/{documentId}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Void> updateStore(
    @PathVariable Long storeId,
    @PathVariable String documentId,
    @RequestBody UpdateStoreDTO dto
  ) {
    Store store = StoreDTOMapper.map(dto);
    return storeFacade.updateStore(storeId, documentId, store)
      .then();
  }
}
