package com.bacan.app.infrastructure.adapter.in.http.mapper.location.state;

import com.bacan.app.domain.models.location.state.State;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.state.StateDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StateDTOMapper {
  public static StateDTO mapToDto(State state) {
    return StateDTO.builder()
      .id(state.id())
      .name(state.name())
      .countryId(state.countryId())
      .build();
  }
}
