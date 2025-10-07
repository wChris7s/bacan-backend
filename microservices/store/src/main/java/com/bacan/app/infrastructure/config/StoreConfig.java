package com.bacan.app.infrastructure.config;

import com.bacan.app.application.facades.ProductFacade;
import com.bacan.app.application.facades.StoreFacade;
import com.bacan.app.application.port.in.http.CategoryUseCase;
import com.bacan.app.application.port.in.http.ProductUseCase;
import com.bacan.app.application.port.in.http.StoreUseCase;
import com.bacan.app.application.port.out.http.UserMicroservice;
import com.bacan.app.application.port.out.persistence.CategoryDatabase;
import com.bacan.app.application.port.out.persistence.ProductDatabase;
import com.bacan.app.application.port.out.persistence.StoreDatabase;
import com.bacan.app.application.services.CategoryService;
import com.bacan.app.application.services.ProductService;
import com.bacan.app.application.services.StoreService;
import com.bacan.app.infrastructure.adapter.out.persistence.CategoryPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.ProductPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.StorePostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.CategoryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductCategoryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProductRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@Configuration
public class StoreConfig {

  // ---- Infra R2DBC
  @Bean
  public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
    return new R2dbcEntityTemplate(connectionFactory);
  }

  // ---- Adapters de persistencia
  @Bean
  public StoreDatabase storeDatabasePort(StoreRepository storeRepository,
                                         R2dbcEntityTemplate template) {
    return new StorePostgresAdapter(storeRepository, template);
  }

  @Bean
  public ProductCategoryRepository productCategoryRepository(ConnectionFactory connectionFactory) {
    return new ProductCategoryRepository(DatabaseClient.create(connectionFactory));
  }

  @Bean
  public ProductDatabase productDatabase(ProductRepository productRepository,
                                         ProductCategoryRepository productCategoryRepository) {
    return new ProductPostgresAdapter(productRepository, productCategoryRepository);
  }

  @Bean
  public CategoryDatabase categoryDatabase(CategoryRepository categoryRepository) {
    return new CategoryPostgresAdapter(categoryRepository);
  }

  // ---- Use cases
  @Bean
  public CategoryUseCase categoryUseCase(CategoryDatabase categoryDatabase) {
    return new CategoryService(categoryDatabase);
  }

  @Bean
  public ProductUseCase productUseCase(ProductDatabase productDatabase) {
    return new ProductService(productDatabase);
  }

  @Bean
  public StoreUseCase storeUseCase(StoreDatabase storeDatabase) {
    return new StoreService(storeDatabase);
  }

  // ---- Stub temporal de UserMicroservice (evita dependencia al adapter HTTP borrado o movido)
  @Bean
  public UserMicroservice userMicroserviceStub() {
    return userId -> Mono.empty(); // si alguien intenta enriquecer, no rompe
  }

  // ---- Facades requeridas por los controllers
  @Bean
  public ProductFacade productFacade(ProductUseCase productUseCase,
                                     CategoryUseCase categoryUseCase,
                                     StoreUseCase storeUseCase) {
    return ProductFacade.builder()
        .productUseCase(productUseCase)
        .categoryUseCase(categoryUseCase)
        .storeUseCase(storeUseCase)
        .build();
  }

  @Bean
  public StoreFacade storeFacade(StoreUseCase storeUseCase, UserMicroservice userMicroservice) {
    return StoreFacade.builder()
        .storeUseCase(storeUseCase)
        .userMicroservice(userMicroservice)
        .build();
  }
}
