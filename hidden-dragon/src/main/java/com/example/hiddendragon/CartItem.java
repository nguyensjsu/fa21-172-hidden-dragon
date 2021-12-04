package com.example.hiddendragon;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@IdClass(CartItemKey.class)
public class CartItem {
  @Id
  private Integer cartId;

  @Id
  private Integer itemId;

  private Integer quantity;

  public Integer getId() {
    return cartId;
  }

  public void setId(Integer cartId) {
    this.cartId = cartId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String toString() {
    return "CartID: " + cartId + " / ItemID: " + itemId + " / Quantity: " + quantity;
  }
  
}

class CartItemKey implements Serializable {
  private Integer cartId;
  private Integer itemId;
}
  