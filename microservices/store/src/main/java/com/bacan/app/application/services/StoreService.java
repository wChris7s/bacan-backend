package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    return database.countByQuery(query);
  }

  @Override
  public Mono<Store> getStoreById(Long storeId) {
    return database.findById(storeId);
  }

  @Override
  public Mono<Store> createStore(Store store) {
    return database.save(store);
  }

  @Override
  public Mono<Store> updateStore(Long storeId, Store store) {
    return database.update(storeId, store);
  }

  @Override
  public Mono<Void> deleteStore(Long storeId) {
    return database.deleteById(storeId);
  }
}
