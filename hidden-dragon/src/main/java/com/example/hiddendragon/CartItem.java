package com.example.hiddendragon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
public class CartItem {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer cart_id;
  private Integer item_id;
  private Integer quantity;

  public Integer getId() {
    return cart_id;
  }

  public void setId(Integer cart_id) {
    this.cart_id = cart_id;
  }

  public Integer getItemId() {
    return item_id;
  }

  public void setItemId(Integer item_id) {
    this.item_id = item_id;
  }

  public String toString() {
    return "CartID: " + cart_id + " / ItemID: " + item_id + " / Quantity: " + quantity;
  }
  
}
  