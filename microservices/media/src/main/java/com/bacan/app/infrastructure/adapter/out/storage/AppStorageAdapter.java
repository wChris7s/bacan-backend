package com.bacan.app.infrastructure.adapter.out.storage;

import com.bacan.app.application.port.out.AppStoragePort;
import com.bacan.app.infrastructure.adapter.out.storage.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class AppStorageAdapter implements AppStoragePort {
  @Override
  public Mono<String> storeFile(String basePath, FilePart filePart) {
    String filename = FileUtil.buildFilename(filePart.filename());
    return filePart.transferTo(new File(basePath + "/" + filename))
      .then(Mono.fromCallable(() -> filename));
  }

  @Override
  public Mono<Void> deleteFile(String basePath, String filename) {
    Path filePath = Paths.get(basePath, filename);
    return Mono.fromCallable(() -> Files.deleteIfExists(filePath))
      .subscribeOn(Schedulers.boundedElastic())
      .flatMap(deleted -> {
        if (deleted) {
          log.info("The file {} was deleted.", filename);
          return Mono.empty();
        }
        log.error("The file {} wasn't deleted.", filename);
        return Mono.error(RuntimeException::new);
      });
  }

  @Override
  public Mono<Resource> retrieveFile(String basePath, String filename) {
    Path filePath = Paths.get(basePath, filename);
    return Mono.fromCallable(() -> {
      if (!Files.exists(filePath)) {
        log.error("The file {} wasn't found.", filename);
        throw new FileNotFoundException();
      }
      log.info("The file {} was retrieved.", filename);
      return new FileSystemResource(filePath.toFile());
    });
  }

  @Override
  public Mono<Boolean> validateFile(String basePath, String filename) {
    Path filePath = Paths.get(basePath, filename);
    return Mono.fromCallable(() -> {
      if (!Files.exists(filePath)) {
        log.error("The file {} does not exists", filename);
        return false;
      }
      log.error("The File {} exists", filename);
      return true;
    });
  }
}
