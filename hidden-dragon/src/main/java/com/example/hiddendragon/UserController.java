package com.example.hiddendragon;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.*;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.*;

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
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;

@RestController // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {

  private final UserRepository userRepository;
  private final CartRepository cartRepository;

  UserController(UserRepository userRepository, CartRepository cartRepository) {

    this.userRepository = userRepository;
    this.cartRepository = cartRepository;
  }

  @Getter
  @Setter
  class  Message{
      private String msg;
      public Message(String m){
          msg = m;
      }
  }
  class  ErrorMessages{
      private ArrayList<Message>  messages = new ArrayList<Message>();
      public void add (String msg){
          messages.add(new Message(msg));
      }
      public ArrayList<Message> getMessages() {return messages;
      }
      public void print(){
          for(Message m : messages){
              System.out.println(m.msg);
          }
      }
  }

  @PostMapping ("/register")// Map ONLY POST Requests (path="/register")
  ResponseEntity<User> newUser (@ModelAttribute User user, Model model) {
      model.addAttribute("user", user);
   
      HttpHeaders responseHeaders = new HttpHeaders();
      //if(user.getPassword() == repeatPassword){
      try {
        User newUser = userRepository.saveAndFlush(user);

        Cart c = new Cart();
        c.setUserId(newUser.getUserId());
        cartRepository.save(c);

        model.addAttribute("isRegistered",true);

        responseHeaders.set("status", HttpStatus.OK + "");

      } catch(Exception e){
        responseHeaders.set("status", HttpStatus.CONFLICT + "");
      }
      

      return new ResponseEntity(responseHeaders, HttpStatus.OK);


   // else{
      // msgs.add("The passwords do not match");
      // msgs.print();
      // return "drugstore";

   // }
    
  }




  @PostMapping("/login") // Map ONLY POST Requests
  ResponseEntity<User> loginUser (@ModelAttribute User user, Model model) {

    HttpHeaders responseHeaders = new HttpHeaders();

    model.addAttribute("user", user);

    User newUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(() -> new UserNotFoundException(user.getUserId()));
    // if(u.isEmpty()){
    //   return "wrongUsePass";
    // } else {
    //   return "item";
    // }
    if(newUser != null){
      responseHeaders.set("status", HttpStatus.OK + "");
      System.out.println("Signed in");
    } else {
      responseHeaders.set("status", HttpStatus.UNAUTHORIZED + "");
      System.out.println("Wrong password or nonexistent user");
    }
   

    return new ResponseEntity(responseHeaders, HttpStatus.OK);
  
    
   
  }




  
}