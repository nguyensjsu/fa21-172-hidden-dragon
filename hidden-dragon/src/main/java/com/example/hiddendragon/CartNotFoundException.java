package com.example.hiddendragon;

class CartNotFoundException extends RuntimeException {

  CartNotFoundException(Integer id) {
    super("Could not find cart for user " + id);
  }
}