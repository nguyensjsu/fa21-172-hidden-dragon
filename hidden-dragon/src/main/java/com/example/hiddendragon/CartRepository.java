package com.example.hiddendragon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;

import com.example.hiddendragon.Cart;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CartRepository extends CrudRepository<Cart, Integer> {
  @Query("SELECT i FROM Cart c INNER JOIN CartItem i ON c.cart_id = i.cart_id WHERE c.user_id = ?1")
  Collection<CartItem> findItems(Integer user_id);
  
}