package com.bacan.app;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaymentApplication {
  @Value("${mercadopago.access_token}")
  private String MP_ACCESS_TOKEN;

  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class, args);
  }

  @PostConstruct
  public void init() {
    MercadoPagoConfig.setAccessToken(MP_ACCESS_TOKEN);
  }
}