package com.bacan.app.infrastructure.adapter.out.http;

import com.bacan.app.application.port.out.http.LocationClient;
import com.bacan.app.infrastructure.adapter.in.http._const.ApiPathConstant;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.FullDistrictDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono; 

@Component
public class LocationClientAdapter implements LocationClient {

  private final WebClient webClient;

  public LocationClientAdapter(
    @NonNull
    @Value("${application.microservice.url.ms-location}") String msLocationUrl) {
    this.webClient = WebClient.create(msLocationUrl + ApiPathConstant.LOCATION_DISTRICT_URL);
  }

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
