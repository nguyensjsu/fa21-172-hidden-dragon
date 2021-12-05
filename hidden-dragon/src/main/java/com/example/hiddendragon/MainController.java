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

@GetMapping
public String home(@ModelAttribute User user, Model model) {
    System.out.println("Redirecting to home");
    
    return "drugstore";
}

@PostMapping("/login")
public String login(@ModelAttribute User user ){
  log.info("User " + user.getUserame() + " attemped login");
  System.out.println(user.getUserame() + " / " + user.getPassword());

  ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/login?username=" + user.getUserame() + "&password=" + user.getPassword(), user, User.class);

  if(response.getHeaders().getFirst("status").equals(HttpStatus.OK + "")){
    return "drugstore";
  } else {
    return ""; // error page (wrong password or account not found)
  }

}

@PostMapping("/register")
public String register(@ModelAttribute User user ){
  log.info("User " + user.getUserame() + " / " + user.getPassword() + " attemped register");
  System.out.println(user.getUserame() + " / " + user.getPassword());

  ResponseEntity<User> response = restTemplate.postForEntity(USERS_URI + "/register?username=" + user.getUserame() + "&password=" + user.getPassword(), user, User.class);

  System.out.println(response.getHeaders().getFirst("status"));

  if(response.getHeaders().getFirst("status").equals(HttpStatus.OK + "")){
    return "drugstore";
  } else {
    return ""; // error page (duplicate signup)
  }

}


  }

