package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.FileManager;
import com.bacan.app.domain.enums.DefaultStorageType;
import com.bacan.app.infrastructure.adapter.in.http.dto.media.MediaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class FileManagerClientAdapter implements FileManager {
  private final WebClient client;

  public FileManagerClientAdapter(WebClient client) {
    this.client = client;
  }

  @Override
  public Mono<Void> validateFile(String filename) {
    return client.get()
      .uri(uriBuilder -> uriBuilder
        .path("/validate/{filename}")
        .build(filename))
      .retrieve()
      .bodyToMono(Void.class)
      .doOnError(e -> {
        log.error("An error occurred validating file: {}", e.getMessage());
        throw new RuntimeException("An error occurred validating file.");
      });
  }

  @Override
  public Mono<String> storeDefaultFileAndGetFilename(DefaultStorageType defaultStorageType) {
    return client.post()
      .uri(uriBuilder -> uriBuilder
        .path("/default/{storageType}")
        .build(defaultStorageType))
      .retrieve()
      .bodyToMono(MediaDTO.class)
      .map(MediaDTO::getFilename)
      .doOnError(e -> {
        log.error("An error occurred storing default file for {}: {}", defaultStorageType, e.getMessage());
        throw new RuntimeException("An error occurred storing default file.");
      });
  }
}
