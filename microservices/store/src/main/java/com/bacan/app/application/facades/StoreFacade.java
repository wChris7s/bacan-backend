package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.http.UserClient;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.domain.models.user.User; // <-- dominio
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
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
        .map(this::mapToDomainUser)      // UserDTO -> User (dominio)
        .map(store::withUser);
  }

  private User mapToDomainUser(com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO dto) {
    // Ajusta campos si tu UserDTO/dominio tienen otros nombres
    return User.builder()
        .name(dto.getName())
        .lastname(dto.getLastname())
        .phone(dto.getPhone())
        .email(dto.getEmail())
        .profilePhotoUrl(dto.getProfilePhotoUrl())
        .build();
  }
}
