package com.bacan.app.infrastructure.adapter.out.persistence.repository;

import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserRoleEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;


public class UserRoleRepository {

  private final DatabaseClient databaseClient;

  public UserRoleRepository(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  public Mono<Void> createUserRole(UserRoleEntity userRoleEntity) {
    return databaseClient.sql("INSERT INTO bacan.user_role(user_id, role_id) VALUES (:userId, :roleId)")
        .bind("userId", userRoleEntity.getUserId())
        .bind("roleId", userRoleEntity.getRoleId())
        .then();
  }
}
