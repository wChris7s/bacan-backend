package com.bacan.app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class DateTimeConfig {
  @Bean
  public Clock clock() {
    return Clock.system(ZoneId.of("America/Lima"));
  }
}
