package com.example.hiddendragon;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drugStore_command")
@Data
@RequiredArgsConstructor
class DSCommand {
    private @Id @GeneratedValue Long id; 
    private String action ;
    private String firstname ;
    private String lastname ;
    private String notes;
    private String phone;
    private String state;
    private String zip;
    private String email;
    private String city;
    private String cardnum;
    private String cardexpyear;
    private String cardcvv;
    private String address;

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
  
    public void setPassword(String password) {
      this.password = password;
    }
  
    public String toString() {
      return "Username: " + username + " / Password: " + password;
    }
}
