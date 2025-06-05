package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.StoreDatabasePort;
import com.bacan.app.domain.model.store.Store;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.StoreEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class StorePostgresAdapter implements StoreDatabasePort{

    private final StoreRepository storeRepository;

    public StorePostgresAdapter(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Mono<Store> createStore(Store store){
        StoreEntity storeEntity= StoreEntityMapper.mapToEntity(store);
        return this.storeRepository.save(storeEntity)
                .map(StoreEntityMapper::mapToModel);
    }

    @Override
    public Flux<Store> findAllStores() {
        return this.storeRepository.findAll()
                .map(StoreEntityMapper::mapToModel);
    }

}
