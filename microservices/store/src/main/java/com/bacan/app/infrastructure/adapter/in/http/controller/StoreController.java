package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.facades.StoreFacade;
import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.CreateStoreDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.store.StoreResponseDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.store.StoreDTOMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping(path = "/bcn/api/store")
public class StoreController {

  private final StoreFacade storeFacade;
  private final StoreUseCase storeUseCase;

  public StoreController(StoreFacade storeFacade, StoreUseCase storeUseCase) {
    this.storeFacade = storeFacade;
    this.storeUseCase = storeUseCase;
  }

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
        .zipWith(storeUseCase.countAllStoresByQuery(storeQuery))
        .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{storeId}")
  public Mono<StoreResponseDTO> getStoreById(@PathVariable Long storeId) {
    return storeFacade.getStoreWithUserById(storeId)
        .map(StoreDTOMapper::mapToDto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<StoreResponseDTO> create(@RequestBody CreateStoreDTO body) {
    Store toCreate = toDomain(body);
    return storeUseCase.createStore(toCreate)
        .flatMap(created -> storeFacade.getStoreWithUserById(created.id()))
        .map(StoreDTOMapper::mapToDto);
  }

  @PutMapping("/{storeId}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<StoreResponseDTO> updateStore(@PathVariable Long storeId, @RequestBody CreateStoreDTO body) {
    Store toUpdate = toDomain(storeId, body);
    return storeUseCase.updateStore(storeId, toUpdate)
        .flatMap(updated -> storeFacade.getStoreWithUserById(updated.id()))
        .map(StoreDTOMapper::mapToDto);
  }

  @DeleteMapping("/{storeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteStore(@PathVariable Long storeId) {
    return storeUseCase.deleteStore(storeId);
  }

  // =======================
  // Helpers: DTO -> Dominio
  // =======================
  private Store toDomain(CreateStoreDTO dto) {
    return Store.builder()
        // tu dominio Store.userId es String, dto.userId es Long:
        .userId(dto.getUserId() == null ? null : String.valueOf(dto.getUserId()))
        .name(dto.getName())
        .story(dto.getStory())
        .open(parseLocalTime(dto.getOpen()))
        .close(parseLocalTime(dto.getClose()))
        .build();
  }

  private Store toDomain(Long id, CreateStoreDTO dto) {
    return Store.builder()
        .id(id)
        .userId(dto.getUserId() == null ? null : String.valueOf(dto.getUserId()))
        .name(dto.getName())
        .story(dto.getStory())
        .open(parseLocalTime(dto.getOpen()))
        .close(parseLocalTime(dto.getClose()))
        .build();
  }

  private LocalTime parseLocalTime(String value) {
    if (value == null || value.isBlank()) return null;
    // Intento 1: ISO (e.g. "08:30", "08:30:00")
    try { return LocalTime.parse(value); } catch (Exception ignored) {}
    // Intento 2: "H:mm"
    try { return LocalTime.parse(value, DateTimeFormatter.ofPattern("H:mm")); } catch (Exception ignored) {}
    // Intento 3: "H:mm:ss"
    try { return LocalTime.parse(value, DateTimeFormatter.ofPattern("H:mm:ss")); } catch (Exception ignored) {}
    // Si nada matchea, devuelve null (o lanza una excepción custom si prefieres validar)
    return null;
  }
}
