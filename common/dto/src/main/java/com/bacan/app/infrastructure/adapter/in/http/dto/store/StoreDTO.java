package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StoreDTO {
    private String userId;
    private Long id;
    private String name;
    private String story;
    private String logo;
    private String opening;
    private String closing;
    private LocalDateTime created;
    private LocalDateTime updated;
    boolean enabled;


}
