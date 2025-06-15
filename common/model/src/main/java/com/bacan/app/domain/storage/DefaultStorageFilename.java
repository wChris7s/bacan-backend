package com.bacan.app.domain.storage;

import lombok.Getter;

@Getter
public enum DefaultStorageFilename {
  USER_STORAGE("default_user.jpg"),
  STORE_STORAGE("default_store.png"),
  PRODUCT_STORAGE("default_product.png");

  private final String filename;

  DefaultStorageFilename(String filename) {
    this.filename = filename;
  }
}
