package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.domain.models.store.Store;
import com.bacan.app.domain.queries.store.StoreQuery;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StoreEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.StoreEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class StorePostgresAdapter implements StoreDatabase {

  private final StoreRepository repo;

  private final R2dbcEntityTemplate template;

  public StorePostgresAdapter(StoreRepository repo, R2dbcEntityTemplate template) {
    this.repo = repo;
    this.template = template;
  }

  private static Query buildQuery(StoreQuery q) {
    Criteria criteria = null;

    if (q.getName() != null && !q.getName().isBlank()) {
      criteria = (criteria == null)
          ? Criteria.where("name").like("%" + q.getName() + "%")
          : criteria.and(Criteria.where("name").like("%" + q.getName() + "%"));
    }

    Query query = (criteria == null) ? Query.empty() : Query.query(criteria);

    Pageable p = q.getPageable();
    if (p != null) {
      Sort sort = p.getSort().isUnsorted()
          ? Sort.by("created_at").descending()
          : Sort.by(p.getSort().stream().map(o -> {
              String prop = Map.of(
                  "id","id",
                  "name","name",
                  "open","open",
                  "close","close",
                  "enabled","enabled",
                  "created_at","created_at",
                  "updated_at","updated_at",
                  "user_id","user_id"
              ).getOrDefault(o.getProperty(), "created_at");
              return o.isAscending() ? Sort.Order.asc(prop) : Sort.Order.desc(prop);
            }).toList());
      query = query.sort(sort).limit(p.getPageSize()).offset(p.getOffset());
    }

    return query;
  }

  @Override
  public Flux<Store> findAllByQuery(StoreQuery q) {
    return template.select(buildQuery(q), StoreEntity.class)
        .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Long> countByQuery(StoreQuery q) {
    Criteria criteria = null;

    if (q.getName() != null && !q.getName().isBlank()) {
      criteria = (criteria == null)
          ? Criteria.where("name").like("%" + q.getName() + "%")
          : criteria.and(Criteria.where("name").like("%" + q.getName() + "%"));
    }

    Query countQuery = (criteria == null) ? Query.empty() : Query.query(criteria);
    return template.count(countQuery, StoreEntity.class);
  }

  @Override
  public Mono<Store> findById(Long storeId) {
    return repo.findById(storeId)
        .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Store> save(Store store) {
    var now = LocalDateTime.now();
    var entity = StoreEntityMapper.mapToEntity(store);
    if (entity.getCreatedAt() == null) entity.setCreatedAt(now);
    entity.setUpdatedAt(now);
    return repo.save(entity).map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Store> update(Long storeId, Store store) {
    return repo.findById(storeId)
        .switchIfEmpty(Mono.error(new IllegalArgumentException("Store " + storeId + " no existe")))
        .flatMap(existing -> {
          var e = StoreEntityMapper.mapToEntity(store);
          e.setId(storeId);
          e.setCreatedAt(existing.getCreatedAt());   // preservar created_at
          e.setUpdatedAt(LocalDateTime.now());
          return repo.save(e);
        })
        .map(StoreEntityMapper::mapToModel);
  }

  @Override
  public Mono<Void> deleteById(Long storeId) {
    return repo.deleteById(storeId);
  }
}
