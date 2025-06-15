package com.bacan.app.infrastructure.adapter.out.storage.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.util.UUID;

@Slf4j
public class FileUtil {
  public static String buildFilename(String filename) {
    return UUID.randomUUID() + "." + FilenameUtils.getExtension(filename);
  }
}
