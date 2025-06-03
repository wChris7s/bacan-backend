package com.bacan.app.domain.model.location.province;

import lombok.Builder;

@Builder
public record Province(
    String id,
    String name,
    String stateId) {
}
