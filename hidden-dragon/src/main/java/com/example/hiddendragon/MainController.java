package com.example.hiddendragon;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called ItemRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private ItemRepository itemRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewItem (@RequestParam String name
      , @RequestParam Integer price) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Item n = new Item();
    n.setName(name);
    n.setPrice(price);
    itemRepository.save(n);
    return "Saved";
  }


  @PostMapping(path="/purchase") // Map ONLY POST Requests
  public @ResponseBody String purchaseItem (@RequestParam Integer id
      , @RequestParam Integer quantity) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Optional<Item> optionalN = itemRepository.findById(id);
    Item n = optionalN.get();
    n.setStock(n.getStock() - quantity);
    itemRepository.save(n);
    return n.toString();
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Item> getAllItems() {
    // This returns a JSON or XML with the users
    return itemRepository.findAll();
  }
}