package com.bacan.app.infrastructure.adapter.in.http.controller;

import lombok.Data;

@Data
public class ProductItem {
  String name;
  String price;
  Integer quantity;
  String image;
}
