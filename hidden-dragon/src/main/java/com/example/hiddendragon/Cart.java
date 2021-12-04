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
  private Integer cartId;

  private Integer userId;

  public Integer getId() {
    return cartId;
  }

  public void setId(Integer cartId) {
    this.cartId = cartId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  
}