package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.domain.utilities.ApplicationTimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class StoreService implements StoreUseCase {
  private final StoreDatabase storeDatabase;

  public StoreService(StoreDatabase storeDatabase) {
    this.storeDatabase = storeDatabase;
  }

  @Override
  public Mono<Store> createStore(Store store) {
    LocalDateTime actualDateTime = ApplicationTimeUtil.getActualDateTime();
    return storeDatabase.createStore(store
      .withEnabled(true)
      .withCreatedAt(actualDateTime)
      .withUpdatedAt(actualDateTime)
    );
  }

  @Override
  public Mono<Store> findStoreById(String storeId) {
    return storeDatabase.findStoreById(storeId);
  }

  @Override
  public Flux<Store> findAllStoresByQuery(StoreQuery storeQuery) {
    return storeDatabase.findAllStores(storeQuery);
  }

  @Override
  public Mono<Long> countAllStoresByQuery(StoreQuery storeQuery) {
    return storeDatabase.countAllStores(storeQuery);
  }
}
