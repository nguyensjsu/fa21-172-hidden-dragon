package com.example.hiddendragon;

class ItemNotFoundException extends RuntimeException {

  ItemNotFoundException(Integer id) {
    super("Could not find item " + id);
  }
}