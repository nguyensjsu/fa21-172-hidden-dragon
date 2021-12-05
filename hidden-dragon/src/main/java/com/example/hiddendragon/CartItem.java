package com.example.hiddendragon;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.ManyToOne;

@Entity
@Data
@Table(name="cart_item")
public class CartItem {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer itemId;

  @ManyToOne 
  private Item item;

  @ManyToOne
  private Cart cart;

  private Integer quantity;

  
}
