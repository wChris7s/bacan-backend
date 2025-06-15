package com.bacan.app.domain.model.location.country;

import lombok.Builder;

@Builder
public record Country(
    Long id,
    String name,
    String phoneCode,
    String langCode) {
}
