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
@Table(name = "country", schema = "bacan")
public class CountryEntity {
  @Id
  private Long id;
  private String name;
  @Column(value = "phone_code")
  private String phoneCode;
  @Column(value = "lang_code")
  private String langCode;
}
