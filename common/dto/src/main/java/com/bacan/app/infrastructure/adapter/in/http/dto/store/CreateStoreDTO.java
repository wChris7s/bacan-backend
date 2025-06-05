package com.bacan.app.infrastructure.adapter.in.http.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Builder
public class CreateStoreDTO {
    private Long userId;
    private String name;
    private String story;
    private String logo;
    private String  opening;
    private String closing;
    private List<Long> roleIds;

    public CreateStoreDTO() {
    }

    public CreateStoreDTO( Long userId, String name, String story, String logo, String opening, String closing, List<Long> roleIds) {
        this.userId = userId;
        this.name = name;
        this.story = story;
        this.logo = logo;
        this.opening = opening;
        this.closing = closing;
        this.roleIds = roleIds;
    }
}
