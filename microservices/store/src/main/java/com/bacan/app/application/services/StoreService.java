package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabasePort;
import com.bacan.app.domain.models.store.Store;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class StoreService implements StoreUseCase {
  private final StoreDatabasePort storeDatabasePort;

  public StoreService(StoreDatabasePort storeDatabasePort) {
    this.storeDatabasePort = storeDatabasePort;
  }

  @Override
  public Mono<Store> createStore(Store store) {
    LocalDateTime actualDateTime = LocalDateTime.now(ZoneId.of("America/Lima"));
    return this.storeDatabasePort.createStore(store
      .withEnabled(true)
      .withCreatedAt(actualDateTime)
      .withUpdatedAt(actualDateTime)
    );
  }

  @Override
  public Flux<Store> getAllStores() {
    return this.storeDatabasePort.findAllStores();
  }
}
