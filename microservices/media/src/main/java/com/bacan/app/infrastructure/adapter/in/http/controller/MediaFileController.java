package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.FileManagerUseCase;
import com.bacan.app.domain.enums.DefaultStorageType;
import com.bacan.app.infrastructure.adapter.in.http.dto.media.MediaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequestMapping("/bcn/api/media")
public class MediaFileController {

  private final FileManagerUseCase fileManagerUseCase;

  public MediaFileController(FileManagerUseCase fileManagerUseCase) {
    this.fileManagerUseCase = fileManagerUseCase;
  }

  @PostMapping()
  public Mono<ResponseEntity<Mono<MediaDTO>>> uploadFile(@RequestPart("file") Mono<FilePart> filePart) {
    return filePart
      .flatMap(fileManagerUseCase::storeFile)
      .flatMap(filename -> Mono.just(ResponseEntity
        .ok(Mono.just(MediaDTO.builder()
          .filename(filename)
          .build()))))
      .onErrorResume(error -> {
        log.error("Error uploading file: {}.", error.getMessage());
        return Mono.just(ResponseEntity.internalServerError().build());
      });
  }

  @DeleteMapping("/{filename}")
  public Mono<ResponseEntity<Object>> deleteFile(@PathVariable String filename) {
    return fileManagerUseCase.deleteFile(filename)
      .then(Mono.fromCallable(() -> ResponseEntity.ok().build()))
      .onErrorResume(error -> {
        log.error("Error deleting file: {}", error.getMessage());
        return Mono.error(() -> new RuntimeException("Ocurrió un error al cargar la imagén"));
      });
  }

  @GetMapping("/{filename}")
  public Mono<ResponseEntity<Mono<Resource>>> getFile(@PathVariable String filename) {
    return fileManagerUseCase.retrieveFile(filename)
      .flatMap(resource -> Mono.just(ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(Mono.just(resource))))
      .onErrorResume(FileNotFoundException.class, e -> {
        log.error("File {} not found.", filename);
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
      }).onErrorResume(ex -> {
        log.error("Error retrieving file: {}.", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
      });
  }

  @GetMapping("/validate/{filename}")
  public Mono<ResponseEntity<Mono<Object>>> validateFile(@PathVariable String filename) {
    return fileManagerUseCase.validateFile(filename)
      .flatMap(isValid -> {
        if (isValid) {
          return Mono.just(ResponseEntity.noContent().build());
        }
        return Mono.just(ResponseEntity.notFound().build());
      });
  }

  @PostMapping("/default/{storageType}")
  public Mono<ResponseEntity<Mono<MediaDTO>>> storeDefaultFile(@PathVariable String storageType) {
    return fileManagerUseCase.storeDefaultFile(DefaultStorageType.valueOf(storageType))
      .flatMap(filename -> Mono.just(ResponseEntity
        .ok(Mono.just(MediaDTO.builder()
          .filename(filename)
          .build()))))
      .onErrorResume(error -> {
        log.error("Error storing default file: {}.", error.getMessage());
        return Mono.just(ResponseEntity.internalServerError().build());
      });
  }
}
