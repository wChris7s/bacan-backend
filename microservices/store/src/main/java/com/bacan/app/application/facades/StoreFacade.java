package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.http.UserClient;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StoreFacade {

  private final StoreUseCase storeUseCase;

  private final UserClient userClient;

  public Flux<Store> getAllStoresWithUserByQuery(StoreQuery query) {
    return storeUseCase.getAllStoresByQuery(query)
      .flatMap(store -> userClient.getUserByDocumentId(store.documentId())
        .map(store::withUser));
  }

  public Mono<Long> countAllStoresByQuery(StoreQuery query) {
    return storeUseCase.countAllStoresByQuery(query);
  }

  public Mono<Store> getStoreWithUserById(Long storeId) {
    return storeUseCase.getStoreByIdOrThrow(storeId)
      .flatMap(store -> userClient.getUserByDocumentId(store.documentId())
        .map(store::withUser));
  }

  public Mono<Store> createStore(String documentId, Store store) {
    return userClient.getUserByDocumentId(documentId)
      .flatMap(user -> storeUseCase
        .createStore(store
          .withDocumentId(documentId)
          .withUser(user)));
  }

  public Mono<Store> updateStore(Long storeId, String documentId, Store store) {
    return userClient.getUserByDocumentId(documentId)
      .flatMap(user -> storeUseCase
        .updateStore(storeId, store
          .withDocumentId(documentId)
          .withUser(user)));
  }
}
