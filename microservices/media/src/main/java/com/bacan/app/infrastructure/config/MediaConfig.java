package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.FileManagerUseCase;
import com.bacan.app.application.port.out.AppStoragePort;
import com.bacan.app.application.port.out.DefaultStoragePort;
import com.bacan.app.application.services.FileManagerService;
import com.bacan.app.infrastructure.adapter.out.storage.AppStorageAdapter;
import com.bacan.app.infrastructure.adapter.out.storage.DefaultStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaConfig {
  @Bean
  public DefaultStoragePort defaultStoragePort() {
    return new DefaultStorageAdapter();
  }

  @Bean
  public AppStoragePort storagePort() {
    return new AppStorageAdapter();
  }

  @Bean
  public FileManagerUseCase fileManagerUseCase(
    @Value("${application.upload.path}") String uploadPath,
    AppStoragePort appStoragePort,
    DefaultStoragePort defaultStoragePort) {
    return new FileManagerService(uploadPath, appStoragePort, defaultStoragePort);
  }
}
