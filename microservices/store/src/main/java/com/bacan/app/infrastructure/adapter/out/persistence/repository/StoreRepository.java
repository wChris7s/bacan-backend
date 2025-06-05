package com.bacan.app.infrastructure.adapter.out.persistence.repository;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.Collection;

public interface StoreRepository extends ReactiveCrudRepository<StoreEntity, Long> {
    Mono<Long> countAllByIdIsIn(Collection<Long> ids);

}
