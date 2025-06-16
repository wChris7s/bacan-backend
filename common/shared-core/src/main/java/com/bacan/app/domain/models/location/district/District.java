package com.bacan.app.domain.models.location.district;

import lombok.Builder;

@Builder
public record District(
  String id,
  String name,
  String provinceId,
  String stateId) {
}
