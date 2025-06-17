package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreDatabase {
  Mono<Store> createStore(Store store);

  Mono<Store> findStoreById(String storeId);

  Flux<Store> findAllStores(StoreQuery query);

  Mono<Long> countAllStores(StoreQuery query);
}
