package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.StoreEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StorePostgresAdapter implements StoreDatabase {

  private final StoreRepository storeRepository;

  static final StoreEntityMapper storeEntityMapper = Mappers.getMapper(StoreEntityMapper.class);

  @Override
  public Flux<Store> findAllByQuery(StoreQuery query) {
    return storeRepository.findAllByQuery(query, query.getPageable())
      .map(storeEntityMapper::toMapper);
  }

  @Override
  public Mono<Long> countAllByQuery(StoreQuery query) {
    return storeRepository.countAllByQuery(query);
  }

  @Override
  public Mono<Store> findById(Long storeId) {
    return storeRepository.findById(storeId)
      .map(storeEntityMapper::toMapper);
  }

  @Override
  public Mono<Store> createStore(Store store) {
    StoreEntity storeEntity = storeEntityMapper.toEntity(store);
    return storeRepository.save(storeEntity)
      .map(storeEntityMapper::toMapper);
  }

  @Override
  public Mono<Store> updateStore(Store store) {
    return this.createStore(store);
  }
}
