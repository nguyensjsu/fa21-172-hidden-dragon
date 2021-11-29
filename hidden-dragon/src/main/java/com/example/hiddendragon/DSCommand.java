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
}
