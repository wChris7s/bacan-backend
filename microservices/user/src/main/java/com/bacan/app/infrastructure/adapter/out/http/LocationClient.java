package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.FullDistrictDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class LocationClient implements com.bacan.app.application.port.out.http.LocationClient {

  @Value("${application.microservice.url.ms-location}")
  private String msLocationUrl;

  private final WebClient webClient = WebClient.create(msLocationUrl);

  @Override
  public Mono<FullDistrictDTO> getFullDistrictById(String id) {
    return webClient.get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/full/{districtId}")
          .build(id))
      .retrieve()
      .bodyToMono(FullDistrictDTO.class);
  }
}
