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
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {
  @Autowired // This means to get the bean called ItemRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;
  private CartRepository cartRepository;

  @PostMapping(path="/register") // Map ONLY POST Requests
  public @ResponseBody String registerUser (@RequestParam String username
      , @RequestParam String password) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setUsername(username);
    n.setPassword(password);
    userRepository.save(n);

    Cart c = new Cart();
    c.setUserId(n.getId());
    cartRepository.save(c);
    
    return "Saved";
  }


  @PostMapping(path="/login") // Map ONLY POST Requests
  public @ResponseBody String loginUser (@RequestParam String username
      , @RequestParam String password) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Collection<User> u = userRepository.findUser(username, password);
    if(u.isEmpty()){
      return "auth failed";
    } else {
      return "auth success for " + u.iterator().next().toString();
    }
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
}