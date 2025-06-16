package com.bacan.app.application.services;

import com.bacan.app.application.port.in.FileManagerUseCase;
import com.bacan.app.application.port.out.AppStoragePort;
import com.bacan.app.application.port.out.DefaultStoragePort;
import com.bacan.app.domain.enums.DefaultStorageType;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public class FileManagerService implements FileManagerUseCase {
  private final String appPath;

  private final AppStoragePort appStoragePort;

  private final DefaultStoragePort defaultStoragePort;

  public FileManagerService(String uploadPath, AppStoragePort appStoragePort, DefaultStoragePort defaultStoragePort) {
    this.appStoragePort = appStoragePort;
    this.appPath = uploadPath + "/app";
    this.defaultStoragePort = defaultStoragePort;
  }

  @Override
  public Mono<String> storeFile(FilePart filePart) {
    return appStoragePort.storeFile(appPath, filePart);
  }

  @Override
  public Mono<Void> deleteFile(String filename) {
    return appStoragePort.deleteFile(appPath, filename);
  }

  @Override
  public Mono<Resource> retrieveFile(String filename) {
    return appStoragePort.retrieveFile(appPath, filename);
  }

  @Override
  public Mono<String> storeDefaultFile(DefaultStorageType defaultStorageType) {
    return defaultStoragePort.storeFile(appPath, defaultStorageType);
  }

  @Override
  public Mono<Boolean> validateFile(String filename) {
    return appStoragePort.validateFile(appPath, filename);
  }
}
