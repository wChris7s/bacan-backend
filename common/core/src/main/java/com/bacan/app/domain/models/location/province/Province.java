package com.bacan.app.domain.models.location.province;

import lombok.Builder;

@Builder
public record Province(
  String id,
  String name,
  String stateId) {
}
