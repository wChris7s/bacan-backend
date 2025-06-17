package com.bacan.app.domain.utilities;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class ApplicationTimeUtil {
  public static LocalDateTime getActualDateTime() {
    return LocalDateTime.now(ZoneId.of("America/Lima"));
  }
}
