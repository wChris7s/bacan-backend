package com.bacan.app.application.port.out.http;

import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import reactor.core.publisher.Mono;

public interface UserClient {
  Mono<UserDTO> getUserByDocumentId(String documentId);
}
