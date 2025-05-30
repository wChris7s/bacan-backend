package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.AddressUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/address")
public class AddressController {
  private final AddressUseCase addressUseCase;

  public AddressController(AddressUseCase addressUseCase) {
    this.addressUseCase = addressUseCase;
  }

  /*
  @GetMapping
  public Flux<AddressDto> getAllAddresses() {
  .map(AddressDtoMapper.AddressDtoMapper)::mapToDto);
  }

  @PostMapping
  public Mono<Void> createAddress(@RequestBody CreateAddressDto createAddressDto) {
    Address address = AddressDtoMapper.mapToModel(createAddressDto);
    return this.addressUseCase.createAddress(address).then();
  }
  */
}