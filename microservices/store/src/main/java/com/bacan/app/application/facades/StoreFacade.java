package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.http.UserMicroservice;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import lombok.Builder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Builder
public class StoreFacade {
  private final StoreUseCase storeUseCase;

  private final UserMicroservice userMicroservice;

  public Flux<Store> getAllStoresWithUserByQuery(StoreQuery storeQuery) {
    return storeUseCase.findAllStoresByQuery(storeQuery)
      .flatMap(this::fillStore);
  }

  public Mono<Store> getStoreWithUserById(String storeId) {
    return storeUseCase.findStoreById(storeId)
      .flatMap(this::fillStore);
  }

  private Mono<Store> fillStore(Store store) {
    return userMicroservice.getUserById(store.userId())
      .map(store::withUser);
  }
}
