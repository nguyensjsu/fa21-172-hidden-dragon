package com.example.hiddendragon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer cart_id;

  private Integer user_id;

  public Integer getId() {
    return cart_id;
  }

  public void setId(Integer cart_id) {
    this.cart_id = cart_id;
  }

  public Integer getUserId() {
    return user_id;
  }

  public void setUserId(Integer user_id) {
    this.user_id = user_id;
  }
  
}