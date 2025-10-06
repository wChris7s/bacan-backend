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
@Table(name = "district", schema = "bacan")
public class DistrictEntity {
  @Id
  private String id;
  private String name;
  @Column(value = "province_id")
  private String provinceId;
}
