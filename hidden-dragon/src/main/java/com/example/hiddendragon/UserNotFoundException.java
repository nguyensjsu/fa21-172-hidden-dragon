package com.example.hiddendragon;

class UserNotFoundException extends RuntimeException {

  UserNotFoundException(Integer id) {
    super("Could not find user " + id);
  }
}