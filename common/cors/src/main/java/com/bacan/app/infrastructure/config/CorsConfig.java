package com.bacan.app.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/bcn/api/**")
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("PUT", "POST", "DELETE", "GET")
        .allowedHeaders("*")
        .exposedHeaders("*");
  }
}
