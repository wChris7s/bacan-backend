package com.bacan.app.infrastructure.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address", schema = "bacan")
public class AddressEntity implements Persistable<String> {
  @Id
  String id;
  @Column(value = "user_id")
  String userId;
  @Column(value = "country_id")
  Long countryId;
  @Column(value = "state_id")
  String stateId;
  @Column(value = "province_id")
  String provinceId;
  @Column(value = "district_id")
  String districtId;
  String street;
  @Column(value = "postal_code")
  String postalCode;
  String number;
  String reference;
  @Column(value = "created_at")
  LocalDateTime createdAt;
  @Column(value = "updated_at")
  LocalDateTime updatedAt;

  @With
  @Transient
  private boolean isNew;

  @Override
  public boolean isNew() {
    return isNew;
  }
}
