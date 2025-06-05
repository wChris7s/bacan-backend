package com.bacan.app.infrastructure.adapter.out.persistence.entity;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@Builder
@Table(name= "store", schema = "bacan")

public class StoreEntity {
    @Id
    private long id;
    @Column(value = "user_id")
    private String userId;  // user_id
    private String name;
    private String story;
    private String logo;
    private String opening;
    private String closing;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
    private boolean enabled;

}
