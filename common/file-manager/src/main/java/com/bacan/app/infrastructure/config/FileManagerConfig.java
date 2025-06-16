package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.out.http.FileManager;
import com.bacan.app.infrastructure.adapter.out.http.FileManagerClientAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FileManagerConfig {
  @Bean
  public FileManager fileManager(@Value("${application.microservice.ms-media}") String baseUrl) {
    return new FileManagerClientAdapter(WebClient.create(baseUrl + "/bcn/api/media"));
  }
}
