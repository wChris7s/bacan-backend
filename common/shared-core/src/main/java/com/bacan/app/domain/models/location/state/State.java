package com.bacan.app.domain.models.location.state;

import lombok.Builder;

@Builder
public record State(
  String id,
  String name,
  Long countryId) {
}
