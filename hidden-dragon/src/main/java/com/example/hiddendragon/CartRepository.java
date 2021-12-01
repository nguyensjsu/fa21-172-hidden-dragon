package com.example.hiddendragon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;

import com.example.hiddendragon.Cart;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CartRepository extends CrudRepository<Cart, Integer> {
  @Query("SELECT i, ci FROM Cart c INNER JOIN CartItem ci ON c.cart_id = ci.cart_id INNER JOIN Item i ON ci.item_id = i.item_id WHERE c.user_id = ?1")
  List<Object[]> findItems(Integer user_id);
  
}