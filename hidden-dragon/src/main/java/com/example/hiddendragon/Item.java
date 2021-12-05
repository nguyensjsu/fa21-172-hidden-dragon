package com.example.hiddendragon;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name="item")
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(unique=true)
  private String name;

  private Integer price;

  private Integer stock;

  public String toString() {
    return "Item name: " + name + " / Price: " + price + " / Remaining Stock: " + stock; 
  }

  
  
  
}