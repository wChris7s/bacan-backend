package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.http.UserClient;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import lombok.Builder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Builder
@Service
public class StoreFacade {
  private final StoreUseCase storeUseCase;

  private final UserClient userClient;

  public Flux<Store> getAllStoresWithUserByQuery(StoreQuery storeQuery) {
    return storeUseCase.getAllStoresByQuery(storeQuery)
      .flatMap(this::fillStore);
  }

  public Mono<Store> getStoreWithUserById(Long storeId) {
    return storeUseCase.getStoreById(storeId)
      .flatMap(this::fillStore);
  }

  private Mono<Store> fillStore(Store store) {
    return userClient.getUserByDocumentId(store.userId())
      .map(store::withUser);
  }
}
