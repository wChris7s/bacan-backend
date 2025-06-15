package com.bacan.app.application.port.out.http;

import com.bacan.app.domain.storage.DefaultStorageType;
import reactor.core.publisher.Mono;

public interface MediaPort {
  Mono<Void> validateFile(String filename);

  Mono<String> storeDefaultFileAndGetFilename(DefaultStorageType defaultStorageType);
}
