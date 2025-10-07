package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StoreService implements StoreUseCase {

  private final StoreDatabase database;

  public StoreService(StoreDatabase database) {
    this.database = database;
  }

  @Override public Flux<Store> findAllStoresByQuery(StoreQuery query) { return database.findAllByQuery(query); }
  @Override public Mono<Long> countAllStoresByQuery(StoreQuery query) { return database.countByQuery(query); }
  @Override public Mono<Store> findStoreById(String storeId) { return database.findById(storeId); }

  @Override public Mono<Store> createStore(Store store) { return database.save(store); }

  @Override public Mono<Store> updateStore(String storeId, Store store) {
    return database.update(storeId, store);
  }

  @Override public Mono<Void> deleteStore(String storeId) { return database.deleteById(storeId); }
}
