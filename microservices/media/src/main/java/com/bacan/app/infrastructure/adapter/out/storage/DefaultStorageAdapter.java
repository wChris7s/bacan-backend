package com.bacan.app.infrastructure.adapter.out.storage;

import com.bacan.app.application.port.out.DefaultStoragePort;
import com.bacan.app.domain.enums.DefaultStorageEnum;
import com.bacan.app.infrastructure.adapter.out.storage.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.nio.file.Files;

@Slf4j
public class DefaultStorageAdapter implements DefaultStoragePort {
  @Override
  public Mono<String> storeFile(String basePath, DefaultStorageEnum defaultStorageEnum) {
    return Mono.fromCallable(() -> {
        String filename = switch (defaultStorageEnum) {
          case USER -> DefaultStorageFilename.USER.getFilename();
          case STORE_LOGO -> DefaultStorageFilename.STORE_LOGO.getFilename();
          case STORE_BACKGROUND -> DefaultStorageFilename.STORE_BACKGROUND.getFilename();
          case PRODUCT -> DefaultStorageFilename.PRODUCT.getFilename();
        };

        String targetFilename = FileUtil.buildFilename(filename);
        File source = new File(basePath + "/default/" + filename);
        File target = new File(basePath + "/" + targetFilename);
        Files.copy(source.toPath(), target.toPath());
        return targetFilename;
      })
      .subscribeOn(Schedulers.boundedElastic())
      .onErrorResume(e -> {
        log.error("Error copying default file: {}.", e.getMessage());
        throw new RuntimeException("Error copying default file.");
      });
  }
}