package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/bcn/api/payment")
public class PaymentController {

  @PostMapping
  public Mono<Map<String, String>> paymentPreference(@RequestBody ProductItems productItems) throws MPException, MPApiException {
    List<PreferenceItemRequest> items = productItems.products.stream()
      .peek(System.out::println)
      .peek(productItem -> productItem.setPrice(productItem.price.substring(0, productItem.price.length() - 2)))
      .peek(System.out::println)
      .map(productItem -> PreferenceItemRequest.builder()
        .id(productItem.quantity + productItem.name)
        .title(productItem.name)
        .pictureUrl(productItem.image)
        .quantity(productItem.quantity)
        .currencyId("PEN")
        .unitPrice(new BigDecimal(productItem.price))
        .build()).toList();

    log.info(items.toString());
    PreferenceRequest preferenceRequest = PreferenceRequest.builder()
      .items(items).build();
    PreferenceClient client = new PreferenceClient();
    Preference preference = client.create(preferenceRequest);
    return Mono.just(Map.of("preference", preference.getId()));
  }
}
