package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreDatabase {
  Flux<Store> findAllByQuery(StoreQuery query);

  Mono<Long> countByQuery(StoreQuery query);

  Mono<Store> findById(Long storeId);

  Mono<Store> save(Store store);

  Mono<Store> update(Long storeId, Store store);

  Mono<Void> deleteById(Long storeId);
}
