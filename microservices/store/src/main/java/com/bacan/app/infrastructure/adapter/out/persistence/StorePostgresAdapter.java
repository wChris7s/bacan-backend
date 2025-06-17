package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.StoreEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class StorePostgresAdapter implements StoreDatabase {
  private final StoreRepository storeRepository;

  public StorePostgresAdapter(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  @Override
  public Mono<Store> createStore(Store store) {
    StoreEntity storeEntity = StoreEntityMapper.mapToEntity(store);
    storeEntity.setNew(true);
    return this.storeRepository.save(storeEntity)
      .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Store> findStoreById(String storeId) {
    return storeRepository.findById(storeId)
      .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Flux<Store> findAllStores(StoreQuery query) {
    return storeRepository.findAllByName(query.getName(), query.getPageable())
      .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Long> countAllStores(StoreQuery query) {
    return storeRepository.countAllByName(query.getName());
  }
}
