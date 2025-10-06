package com.bacan.app.infrastructure.adapter.out.storage;

import lombok.Getter;

@Getter
public enum DefaultStorageFilename {
  USER("default_user.jpg"),
  STORE_LOGO("default_store.png"),
  STORE_BACKGROUND("default_store_background.png"),
  PRODUCT("default_product.png");

  private final String filename;

  DefaultStorageFilename(String filename) {
    this.filename = filename;
  }
}
