package com.example.hiddendragon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer user_id;

  private String username;

  private String password;

  public Integer getId() {
    return user_id;
  }
  
  public String getUserame() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String price) {
    this.password = password;
  }

  public String toString() {
    return "Username: " + username + " / Password: " + password;
  }
  
  
}