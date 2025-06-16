package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.CreateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.store.StoreDTOMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/store")

public class StoreController {
  private final StoreUseCase storeUseCase;

  public StoreController(StoreUseCase storeUseCase) {
    this.storeUseCase = storeUseCase;
  }

  @GetMapping
  public Flux<StoreDTO> getAllStores() {
    return this.storeUseCase.getAllStores()
      .map(StoreDTOMapper::mapToDto);
  }

  @PostMapping
  public Mono<Void> createStore(@RequestBody CreateStoreDTO createStoreDTO) {
    Store store = StoreDTOMapper.mapToModel(createStoreDTO);
    return this.storeUseCase.createStore(store).then();
  }
}
