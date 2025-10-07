package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreUseCase {
  Flux<Store> getAllStoresByQuery(StoreQuery query);

  Mono<Long> countAllStoresByQuery(StoreQuery query);

  Mono<Store> getStoreById(Long storeId);

  Mono<Store> createStore(Store store);

  Mono<Store> updateStore(Long storeId, Store store);

  Mono<Void> deleteStore(Long storeId);
}
