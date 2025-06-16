package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.models.store.Store;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreUseCase {
  Mono<Store> createStore(Store store);

  Flux<Store> getAllStores();
}
