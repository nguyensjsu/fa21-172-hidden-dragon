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

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CartRepository cartRepository;

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
        User newUser = userRepository.save(user);

        Cart c = new Cart();
        c.setUserId(newUser.getId());
        cartRepository.save(c);

        model.addAttribute("isRegistered",true);

        return new ResponseEntity(user, HttpStatus.OK);

      } catch(Exception e){
        return new ResponseEntity(user, HttpStatus.CONFLICT);
      }
    
  }




  @PostMapping("/login") // Map ONLY POST Requests
  ResponseEntity<User> loginUser (@ModelAttribute User user, Model model) {

    HttpHeaders responseHeaders = new HttpHeaders();

    User newUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    // if(u.isEmpty()){
    //   return "wrongUsePass";
    // } else {
    //   return "item";
    // }
    if(newUser != null){    
      System.out.println("Signed in");
      return new ResponseEntity(newUser, HttpStatus.OK);  
    } else {
      System.out.println("Wrong password or nonexistent user");
      return new ResponseEntity(newUser, HttpStatus.UNAUTHORIZED);
    }
   
  }

  @PostMapping ("/reset")// Map ONLY POST Requests (path="/register")
  ResponseEntity<User> resetPassword (@RequestParam("username") String username, @RequestParam("newPassword") String newPassword, @RequestParam("existingPassword") String existingPassword) {
      User user = userRepository.findByUsernameAndPassword(username, existingPassword);

     
       try {
        
        if(user == null) {
          return new ResponseEntity(user, HttpStatus.BAD_REQUEST);
        } else if(!user.getPassword().equals(existingPassword)){
          return new ResponseEntity(user, HttpStatus.UNAUTHORIZED);
        } else {
          user.setPassword(newPassword);
          userRepository.save(user);
          return new ResponseEntity(user, HttpStatus.OK);
        }

        

      } catch(Exception e){
        return new ResponseEntity(user, HttpStatus.BAD_REQUEST);
      }
    
  }
  
}