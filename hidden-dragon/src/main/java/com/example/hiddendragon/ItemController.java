package com.example.hiddendragon;

import java.util.Optional;
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

@RestController // This means that this class is a Controller
@RequestMapping(path="/items") // This means URL's start with /demo (after Application path)
public class ItemController {
  private final ItemRepository itemRepository;
  private final ItemModelAssembler assembler;

  ItemController(ItemRepository itemRepository, ItemModelAssembler assembler) {

    this.itemRepository = itemRepository;
    this.assembler = assembler;
  }

  

  @GetMapping("/")
  CollectionModel<EntityModel<Item>> allItems() {
  
    List<EntityModel<Item>> items = itemRepository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());
  
    return CollectionModel.of(items, linkTo(methodOn(ItemController.class).allItems()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<Item> oneItem(@PathVariable Integer id) {
  
    Item item = itemRepository.findById(id) //
        .orElseThrow(() -> new ItemNotFoundException(id));
  
    return assembler.toModel(item);
  }

  @PostMapping(path="/") // Map ONLY POST Requests
  ResponseEntity<EntityModel<Item>> newItem (@RequestBody Item item) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Item newItem = itemRepository.save(item);
    return ResponseEntity //
    .created(linkTo(methodOn(ItemController.class).oneItem(newItem.getId())).toUri()) //
    .body(assembler.toModel(newItem));
  }


  @PostMapping(path="/{id}/purchase") // Map ONLY POST Requests
  ResponseEntity<?> purchaseItem (@PathVariable Integer id) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    System.out.println(item.getStock());
    if(item.getStock() == 0){
      return ResponseEntity //
      .status(HttpStatus.METHOD_NOT_ALLOWED) //
      .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
      .body(Problem.create() //
          .withTitle("Method not allowed") //
          .withDetail("You can't order this item " + item.getStock() + " left"));
    } else {
      item.setStock(item.getStock() - 1);
      itemRepository.save(item);
      return ResponseEntity.ok(assembler.toModel(itemRepository.save(item)));
    }
    
  
  }
}