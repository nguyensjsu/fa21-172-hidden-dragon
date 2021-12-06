package com.example.hiddendragon;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.*;
import org.springframework.ui.Model;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.problem.Problem;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController // This means that this class is a Controller
@RequestMapping(path="/carts") // This means URL's start with /demo (after Application path)
public class CartController {
  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartItemRepository cartItemRepository;

  @Autowired
  private RabbitMqReceiver receiver;

  @Bean
  public RabbitMqReceiver receiver() {
      return new RabbitMqReceiver();
  }

  @GetMapping
  ResponseEntity<ArrayList<CartItem>> getItems(@RequestParam("userId") Integer id, Model model) {
    Cart cart = cartRepository.findByUserId(id);
    
    ArrayList<CartItem> items = cartItemRepository.findByCart(cart);
    for(CartItem i : items){
      System.out.println(i);
    }

    
    return new ResponseEntity(items, HttpStatus.OK);
    
  }

  @GetMapping("/total")
  ResponseEntity<Integer> totalCartAction(@RequestParam("userId") Integer id, Model model) {
    Cart cart = cartRepository.findByUserId(id);
    
    ArrayList<CartItem> items = cartItemRepository.findByCart(cart);
    Integer total = 0;
    for(CartItem i : items){
      total += (i.getQuantity() * i.getItem().getPrice());
    }

    
    return new ResponseEntity(total, HttpStatus.OK);
    
  }

  @GetMapping("/clear")
  ResponseEntity<String> clearCartAction(@RequestParam("userId") Integer id, Model model) {
    Cart cart = cartRepository.findByUserId(id);
    
    ArrayList<CartItem> items = cartItemRepository.findByCart(cart);
    
    for(CartItem i: items){
      cartItemRepository.deleteById(i.getId());
    }
    
    return new ResponseEntity("items cleared", HttpStatus.OK);
    
  }
  
  @PostMapping("/add")
  ResponseEntity<CartItem> addItem(@RequestParam("userId") Integer id, @RequestParam("quantity") Integer quantity, @ModelAttribute Item item, Model model) {
    System.out.println("ok");
    Cart cart = cartRepository.findByUserId(id);

    HttpHeaders responseHeaders = new HttpHeaders();

    CartItem ci = new CartItem();
    ci.setItem(item);
    ci.setCart(cart);
    ci.setQuantity(quantity);
    cartItemRepository.save(ci);
   return new ResponseEntity(ci, HttpStatus.OK);
  }

}