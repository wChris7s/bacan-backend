package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.location.state.State;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.StateEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StateEntityMapper {
  public static State mapToModel(StateEntity stateEntity) {
    return State.builder()
      .id(stateEntity.getId())
      .name(stateEntity.getName())
      .countryId(stateEntity.getCountryId())
      .build();
  }

  public static StateEntity mapToEntity(State state) {
    return StateEntity.builder()
      .id(state.id())
      .name(state.name())
      .countryId(state.countryId())
      .build();
  }
}
