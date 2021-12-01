package com.example.hiddendragon;

import java.util.Optional;
import java.util.Collection;
import java.util.List;
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

  @Autowired
  private CartItemRepository cartItemRepository;

  @GetMapping(path="/find") // Map ONLY POST Requests
  public @ResponseBody String findItems (@RequestParam Integer id) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    List<Object[]> u = cartRepository.findItems(id);
    String output = "";
    for(Object o[]: u){
      Item i = (Item) o[0];
      CartItem ci = (CartItem) o[1];
      output += i.toString() + " " + ci.toString() + "\n";
    }
    return output;
  }

  @PostMapping(path="/add")
  public @ResponseBody String addItem(@RequestParam Integer id, @RequestParam Integer item_id, @RequestParam Integer quantity) {
    Cart u = cartRepository.findTopCart(id);
    Integer cartId = u.getId();
    CartItem ci = new CartItem();
    ci.setId(cartId);
    ci.setItemId(item_id);
    ci.setQuantity(quantity);
    cartItemRepository.save(ci);

    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Cart> getAllCarts() {
    // This returns a JSON or XML with the users
    return cartRepository.findAll();
  }
}