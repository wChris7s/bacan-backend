package com.bacan.app.application.port.out.http;

import com.bacan.app.domain.enums.DefaultStorageEnum;
import reactor.core.publisher.Mono;

public interface FileManager {
  Mono<Void> validateFile(String filename);

  Mono<String> storeDefaultFileAndGetFilename(DefaultStorageEnum defaultStorageEnum);
}
