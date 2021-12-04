package com.example.hiddendragon;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.*;
import org.springframework.ui.Model;

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
@RequestMapping(path="/cart") // This means URL's start with /demo (after Application path)
public class CartController {
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final CartModelAssembler assembler;

  CartController(CartRepository cartRepository,CartItemRepository cartItemRepository, CartModelAssembler assembler) {
    this.cartItemRepository = cartItemRepository;
    this.cartRepository = cartRepository;
    this.assembler = assembler;
  }

  @GetMapping("/")
  CollectionModel<EntityModel<Cart>> allCarts() {
  
    List<EntityModel<Cart>> carts = cartRepository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());
  
    return CollectionModel.of(carts, linkTo(methodOn(CartController.class).allCarts()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<Cart> oneCart(@PathVariable Integer id) {
  
    Cart cart = cartRepository.findByUserId(id) //
        .orElseThrow(() -> new CartNotFoundException(id));
  
    return assembler.toModel(cart);
  }

  @PostMapping("/{id}/add")
  ResponseEntity<EntityModel<Cart>> addItem(@PathVariable Integer id, @ModelAttribute Item item, Model model) {
    Cart cart = cartRepository.findByUserId(id).orElseThrow(()-> new CartNotFoundException(id));
    CartItem ci = new CartItem();
    ci.setId(cart.getId());
    ci.setItemId(item.getId());
    ci.setQuantity(1);
    cartItemRepository.save(ci);

    return ResponseEntity //
    .created(linkTo(methodOn(CartController.class).oneCart(cart.getId())).toUri()) //
    .body(assembler.toModel(cart));
  }

}