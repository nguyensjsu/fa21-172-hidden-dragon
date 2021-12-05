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
import org.springframework.ui.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController // This means that this class is a Controller
@RequestMapping(path="/items") // This means URL's start with /demo (after Application path)
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  ResponseEntity<List<Item>> getItems() {
    List<Item> items = itemRepository.findAll();
    return new ResponseEntity(items, HttpStatus.OK);
  }

  @PostMapping 
  ResponseEntity<Item> newItem (@ModelAttribute Item item, Model model) {
      model.addAttribute("item", item);
      try {
        Item newItem = itemRepository.save(item);
        return new ResponseEntity(item, HttpStatus.OK);

      } catch(Exception e){
        return new ResponseEntity(item, HttpStatus.CONFLICT);
      }
    
  }
  
}