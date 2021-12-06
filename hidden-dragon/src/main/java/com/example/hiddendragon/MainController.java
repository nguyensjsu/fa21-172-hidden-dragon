package com.example.hiddendragon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    //run on docker
    private String USERS_URI = "http://localhost:8080/users";
    private String CARTS_URI = "http://localhost:8080/carts";
    private String ITEM_URI = "http://localhost:8080/items";
    private String PAYMENT_URI = "http://localhost:8080/payments";

  @GetMapping
  public String home(@ModelAttribute User user, Model model) {
      System.out.println("Redirecting to home");
      
      return "drugstore";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute User user, Model model ){
    log.info("User " + user.getUsername() + " attemped login");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    
    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/login?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);
      return "redirect:/store";
    } catch(Exception e) {
      return "wrongUsePass"; 
    }

  }

  @PostMapping("/register")
  public String register(@ModelAttribute User user ){
    log.info("User " + user.getUsername() + " / " + user.getPassword() + " attemped register");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/register?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);
      
      return "after_reg";
    } catch(Exception e) {
      return "wrongDupAcc";
    }
    
  }

  @PostMapping("/reset")
  public String reset(@RequestParam("username") String username, @RequestParam("existingPassword") String existingPassword, @RequestParam("newPassword") String newPassword, Model model ){
    System.out.println("Resetting password for" + username + " " + existingPassword + " new password: " + newPassword);
    try {
      ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/reset?username=" + username + "&existingPassword=" + existingPassword + "&newPassword=" + newPassword, null, User.class);
      System.out.println(response.toString());
      return "after_reg";
    } catch(Exception e) {
      return "error";
    }
    
  }
  

  @GetMapping("/store")
  public String getStore(Model model){
    System.out.println("Accessing page store");

    try {
      ArrayList<Item> items = new ArrayList<Item>();
      ResponseEntity<ArrayList> response = restTemplate.getForEntity(ITEM_URI, ArrayList.class);
      ObjectMapper mapper = new ObjectMapper();
      System.out.println(response.getBody().toString());
      for(Object item: response.getBody()){
        Item storeItem = new Item();

        storeItem = mapper.convertValue(item, Item.class);
        items.add(storeItem);
        System.out.println(storeItem);
      }
      model.addAttribute("user", 1);
      model.addAttribute("items", items);
      return "item";
    } catch(Exception e)  {
      return "error";
    }
    

  }

  @GetMapping("/error")
  public String getErrorpage(){
    return "error";

  }

  @GetMapping("/checkout")
  public String getCheckout(@ModelAttribute DSCommand command, Model model){ 
    return "checkout";
  }

  @PostMapping("/shopping")
  public String initiateCheckout(@ModelAttribute Item item, @RequestParam("userId") Integer id, @RequestParam("quantity") Integer quantity, Model model){
    System.out.println("Adding item to cart" + item.getName() + item.getId() + "/" + id + "/" + quantity);
    System.out.println(CARTS_URI + "/add?userId="+ id + "&quantity=" + quantity + "&name=" + item.getName() + "&price=" + item.getPrice() + "&stock=" + item.getStock() + "&id=" + item.getId());
    ResponseEntity<CartItem> response = restTemplate.postForEntity(CARTS_URI + "/add?userId="+ id + "&quantity=" + quantity + "&name=" + item.getName() + "&price=" + item.getPrice() + "&stock=" + item.getStock() + "&id=" + item.getId(), item, CartItem.class);
    System.out.println(response.getBody());
    return "checkout";
  }

  @GetMapping("/shopping")
  public String getCart(@RequestParam("userId") Integer id, Model model ){
    System.out.println("User " + id + "accessing cart");

    try {
      ArrayList<CartItem> items = new ArrayList<CartItem>();
      ResponseEntity<ArrayList> response = restTemplate.getForEntity(CARTS_URI, ArrayList.class, id);
      ResponseEntity<Integer> totalResponse = restTemplate.getForEntity(CARTS_URI + "/total?=" + id, Integer.class, id);
      ObjectMapper mapper = new ObjectMapper();

      for(Object item: response.getBody()){
        CartItem cartItem = new CartItem();

        cartItem = mapper.convertValue(cartItem, CartItem.class);
        items.add(cartItem);
        System.out.println(cartItem);
      }

      model.addAttribute("items", items);
      model.addAttribute("total", totalResponse.getBody());

      return "checkout";
    } catch(Exception e)  {
      return "error";
    }

  }


  }

