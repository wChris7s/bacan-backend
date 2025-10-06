package com.bacan.app.infrastructure.adapter.in.http.controller;

import lombok.Data;

import java.util.List;

@Data
public class ProductItems {
  List<ProductItem> products;
}
