package com.bacan.app.application.port.out;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface AppStoragePort {
  Mono<String> storeFile(String basePath, FilePart filePart);

  Mono<Void> deleteFile(String basePath, String filename);

  Mono<Resource> retrieveFile(String basePath, String filename);

  Mono<Boolean> validateFile(String basePath, String filename);
}
