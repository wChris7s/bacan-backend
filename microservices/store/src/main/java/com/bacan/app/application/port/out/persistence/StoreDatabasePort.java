package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.store.Store;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreDatabasePort {
    Mono<Store> createStore(Store store);
    Flux<Store> findAllStores();
}
