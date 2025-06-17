package com.bacan.app.application.port.out;

import com.bacan.app.domain.enums.DefaultStorageEnum;
import reactor.core.publisher.Mono;

public interface DefaultStoragePort {
  Mono<String> storeFile(String basePath, DefaultStorageEnum defaultStorageEnum);
}
