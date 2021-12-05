package com.example.hiddendragon;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.*;

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
  private final UserModelAssembler assembler;

  UserController(UserRepository userRepository, CartRepository cartRepository, UserModelAssembler assembler) {

    this.userRepository = userRepository;
    this.assembler = assembler;
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

  @GetMapping("/")
  CollectionModel<EntityModel<User>> allUsers() {
  
    List<EntityModel<User>> users = userRepository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

 
    return CollectionModel.of(users, linkTo(methodOn(UserController.class).allUsers()).withSelfRel());
    
  }

  @GetMapping("/{id}")
  EntityModel<User> oneUser(@PathVariable Integer id) {
  
    User user = userRepository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));
  
    return assembler.toModel(user);
  }
 
 

  @PostMapping ("/register")// Map ONLY POST Requests (path="/register")
  ResponseEntity<User> newUser (@ModelAttribute User user, Model model) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    model.addAttribute("user", user);
   
    HttpHeaders responseHeaders = new HttpHeaders();
    //if(user.getPassword() == repeatPassword){
      
      User newUser = userRepository.save(user);
  
      Cart c = new Cart();
      c.setUserId(newUser.getId());
      cartRepository.save(c);

      model.addAttribute("isRegistered",true);

      responseHeaders.set("status", HttpStatus.OK + "");

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

    User newUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(() -> new UserNotFoundException(user.getId()));
    // if(u.isEmpty()){
    //   return "wrongUsePass";
    // } else {
    //   return "item";
    // }
    responseHeaders.set("status", HttpStatus.OK + "");

    return new ResponseEntity(responseHeaders, HttpStatus.OK);
  
    
   
  }




  
}