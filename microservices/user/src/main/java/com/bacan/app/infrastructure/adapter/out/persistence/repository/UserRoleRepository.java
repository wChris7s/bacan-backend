package com.bacan.app.infrastructure.adapter.out.persistence.repository;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserRoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface UserRoleRepository extends ReactiveCrudRepository<UserRoleEntity, Long>{
}
