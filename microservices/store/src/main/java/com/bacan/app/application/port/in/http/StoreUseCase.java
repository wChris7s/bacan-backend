package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreUseCase {
  Flux<Store> findAllStoresByQuery(StoreQuery query);
  Mono<Long> countAllStoresByQuery(StoreQuery query);
  Mono<Store> findStoreById(String storeId);

  // NUEVOS para CRUD:
  Mono<Store> createStore(Store store);
  Mono<Store> updateStore(String storeId, Store store);
  Mono<Void> deleteStore(String storeId);
}
