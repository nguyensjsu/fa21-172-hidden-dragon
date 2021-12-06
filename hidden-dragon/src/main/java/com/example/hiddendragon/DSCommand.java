package com.example.hiddendragon;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

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
    private String cardexpmon;
    private String cardexpyear;
    private String cardcvv;
    private String address;


    private String cardType;

    // for executing payment through CyberSource

    private String ordernumber;
  
    private String transactionamount;
  
    private String transactioncurrency;
 
    private String authid;
 
    private String authstatus;

    private String captureid;
  
    private String capturestatus;
 
    private String cost;
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
    
    public String getCardType(){
        return cardType;
    }
  
    public void setCardType(String cardType){
        this.cardType = cardType;
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
    String firstname(){return firstname;}
    String lastname(){return lastname;}
    String address(){return address;}
    String city(){return city;}
    String state(){return state;}
    String zip(){return zip;}
    String phone(){return phone;}
    String cardnum(){return cardnum;}
    String cardexpmon(){return cardexpmon;}
    String cardexpyear(){return cardexpyear;}
    String transactionamount(){return transactionamount;}
    String cardcvv(){return cardcvv;}
    String email(){return email;}
    String notes(){return notes;}
    
}
