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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

  @GetMapping
  public String home(@ModelAttribute User user, Model model) {
      System.out.println("Redirecting to home");
      
      return "drugstore";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute User user ){
    log.info("User " + user.getUsername() + " attemped login");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/login?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);

    if(response.getStatusCode() == HttpStatus.OK){
      return "checkout";
    } else {
      return "wrongUsePass"; // error page (wrong password or account not found)
    }

  }

  @PostMapping("/register")
  public String register(@ModelAttribute User user ){
    log.info("User " + user.getUsername() + " / " + user.getPassword() + " attemped register");
    System.out.println(user.getUsername() + " / " + user.getPassword());

    ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/register?username=" + user.getUsername() + "&password=" + user.getPassword(), user, User.class);

    System.out.println(response.getStatusCode());

    if(response.getStatusCode().equals(HttpStatus.OK)){
      return "after_reg";
    } else {
      return "wrongUsePass"; // error page (duplicate signup)
    }

  }

  @PostMapping("/cart")
  public String getCart(){

    ResponseEntity<CartItem> response = restTemplate.getForEntity(CARTS_URI, CartItem.class);

    if(response.getStatusCode().equals(HttpStatus.OK)){
      return "after_reg";
    } else {
      return "wrongUsePass"; // error page (duplicate signup)
    }

  }


  }
