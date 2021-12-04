package com.example.hiddendragon;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class CartModelAssembler implements RepresentationModelAssembler<Cart, EntityModel<Cart>> {

  @Override
  public EntityModel<Cart> toModel(Cart cart) {

    return EntityModel.of(cart, //
        linkTo(methodOn(CartController.class).oneCart(cart.getId())).withSelfRel(),
        linkTo(methodOn(CartController.class).allCarts()).withRel("carts"));
  }
}