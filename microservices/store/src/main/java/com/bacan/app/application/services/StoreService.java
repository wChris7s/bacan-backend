package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StoreService implements StoreUseCase {

  private final StoreDatabase database;

  public StoreService(StoreDatabase database) {
    this.database = database;
  }

  @Override
  public Flux<Store> getAllStoresByQuery(StoreQuery query) {
    return database.findAllByQuery(query);
  }

  @Override
  public Mono<Long> countAllStoresByQuery(StoreQuery query) {
    return database.countAllByQuery(query);
  }

  @Override
  public Mono<Store> getStoreByIdOrThrow(Long storeId) {
    return database.findById(storeId)
      .switchIfEmpty(Mono.defer(() -> {
        log.error("Store with Id {} not found.", storeId);
        throw new RuntimeException("Store not found");
      }));
  }

  @Override
  public Mono<Store> createStore(Store store) {
    return database.createStore(store.withEnabled(true))
      .doOnSuccess(savedUser -> log.info("Store with Id {} was created.", store.id()))
      .doOnError(e -> log.error("An error occurred while trying to create store: {}", e.getMessage()));
  }

  @Override
  public Mono<Store> updateStore(Long storeId, Store store) {
    return database.findById(storeId)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Store not found"))))
      .map(savedStore -> savedStore
        .withOpen(store.open())
        .withClose(store.close())
        .withStory(store.story()))
      .flatMap(database::updateStore)
      .doOnSuccess(savedUser -> log.info("Store with Id {} was updated.", store.id()))
      .doOnError(e -> log.error("An error occurred while trying to update store: {}", e.getMessage()));
  }
}
