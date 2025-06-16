package com.bacan.app.application.port.out.http;

import com.bacan.app.domain.enums.DefaultStorageType;
import reactor.core.publisher.Mono;

public interface FileManager {
  Mono<Void> validateFile(String filename);

  Mono<String> storeDefaultFileAndGetFilename(DefaultStorageType defaultStorageType);
}
