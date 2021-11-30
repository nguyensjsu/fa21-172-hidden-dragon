package com.example.hiddendragon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;

import com.example.hiddendragon.CartItem;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

}