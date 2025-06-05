package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.StoreUseCase;
import com.bacan.app.application.port.out.persistence.StoreDatabasePort;
import com.bacan.app.application.services.StoreService;
import com.bacan.app.infrastructure.adapter.out.persistence.StorePostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StoreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class StoreConfig {
    @Bean
    public StoreDatabasePort storeDatabasePort(StoreRepository storeRepository) {
        return new StorePostgresAdapter(storeRepository);

    }
    @Bean
    public StoreUseCase storeUseCase(StoreRepository repository) {
        return new StoreService(storeDatabasePort(repository));
    }
}
