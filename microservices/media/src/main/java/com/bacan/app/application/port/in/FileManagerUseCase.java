package com.bacan.app.application.port.in;

import com.bacan.app.domain.enums.DefaultStorageEnum;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface FileManagerUseCase {
  Mono<String> storeFile(FilePart filePart);

  Mono<Void> deleteFile(String filename);

  Mono<Resource> retrieveFile(String filename);

  Mono<String> storeDefaultFile(DefaultStorageEnum defaultStorageEnum);

  Mono<Boolean> validateFile(String filename);
}
