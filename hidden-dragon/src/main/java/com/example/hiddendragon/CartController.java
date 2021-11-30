package com.example.hiddendragon;

import java.util.Optional;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/cart") // This means URL's start with /demo (after Application path)
public class CartController {
  @Autowired // This means to get the bean called ItemRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private CartRepository cartRepository;

  @PostMapping(path="/find") // Map ONLY POST Requests
  public @ResponseBody String findItems (@RequestParam Integer id) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Collection<CartItem> u = cartRepository.findItems(id);
    String output = "";
    for(CartItem i: u){
      output += i.toString() + "\n";
    }
    return output;
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Cart> getAllCarts() {
    // This returns a JSON or XML with the users
    return cartRepository.findAll();
  }
}