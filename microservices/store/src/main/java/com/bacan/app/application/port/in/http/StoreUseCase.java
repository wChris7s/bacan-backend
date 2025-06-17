package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreUseCase {
  Mono<Store> createStore(Store store);

  Mono<Store> findStoreById(String storeId);

  Flux<Store> findAllStoresByQuery(StoreQuery storeQuery);

  Mono<Long> countAllStoresByQuery(StoreQuery storeQuery);
}
