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
@Table(name = "state", schema = "bacan")
public class StateEntity {
  @Id
  private String id;
  private String name;
  @Column(value = "country_id")
  private Long countryId;
}
