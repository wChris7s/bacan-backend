package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "province", schema = "bacan")
public class ProvinceEntity {
  @Id
  private String id;
  private String name;
  @Column(value = "state_id")
  private String stateId;
}
