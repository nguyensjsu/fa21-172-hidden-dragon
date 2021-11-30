package com.example.hiddendragon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer item_id;

  private String item_name;

  private Integer price;

  private Integer stock;

  public Integer getId() {
    return item_id;
  }

  public void setId(Integer item_id) {
    this.item_id = item_id;
  }

  public String getName() {
    return item_name;
  }

  public void setName(String item_name) {
    this.item_name = item_name;
  }


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String toString() {
    return "Item name: " + item_name + " / Price: " + price + " / Remaining Stock: " + stock; 
  }
  
  
}